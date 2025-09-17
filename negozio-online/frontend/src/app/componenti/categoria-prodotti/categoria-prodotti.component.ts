import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, switchMap, map, take } from 'rxjs';
import { Prodotto } from '../../modelli/prodotto.model';
import { ProdottoService } from '../../servizi/prodotto.service';
import { Store } from '@ngrx/store';
import { AppState } from '../../store/app.state';
import { aggiungiAlCarrello } from '../../store/actions/prodotto.actions';
import { selezionaCarrello } from '../../store/selectors/prodotto.selectors';

@Component({
  selector: 'app-categoria-prodotti',
  templateUrl: './categoria-prodotti.component.html',
  styleUrls: ['./categoria-prodotti.component.css']
})
export class CategoriaProdottiComponent implements OnInit {

  prodotti$!: Observable<Prodotto[]>;
  prodottiOriginali: Prodotto[] = [];
  nomeCategoria: string = 'Caricamento...';
  ordinamento: string = 'alfabetico';
  private readonly backendUrl = 'http://localhost:8080';

  private mappaCategorie: { [key: number]: string } = {
    1: 'Vini Rossi', 2: 'Vini Bianchi', 3: 'Vini Rosati',
    4: 'Vini da 375 ml', 5: 'Vini Spumanti', 6: 'Vini Dolci'
  };

  constructor(
    private route: ActivatedRoute,
    private prodottoService: ProdottoService,
    private store: Store<AppState>
  ) { }

  ngOnInit(): void {
    this.prodotti$ = this.route.paramMap.pipe(
      switchMap(params => {
        const id = Number(params.get('id'));
        this.nomeCategoria = this.mappaCategorie[id] || 'Categoria';
        return this.prodottoService.ottieniProdottiPerCategoria(id);
      }),
      map(prodotti => {
        this.prodottiOriginali = [...prodotti];
        return this.ordinaProdotti(prodotti, this.ordinamento);
      })
    );
  }

  getUrlImmagine(prodotto: Prodotto): string {
    if (!prodotto.urlImmagine) return 'assets/immagini/no-image.png'; 
    if (prodotto.urlImmagine.startsWith('http')) return prodotto.urlImmagine;
    return `${this.backendUrl}${prodotto.urlImmagine}`;
  }
  
  aggiungiProdottoAlCarrello(prodotto: Prodotto): void {
    if (prodotto.quantita > 0) {
      // Controllo se il prodotto è già nel carrello
      this.store.select(selezionaCarrello).pipe(take(1)).subscribe(carrello => {
        const elementoEsistente = carrello.find(elem => elem.prodotto.id === prodotto.id);
        const quantitaGiaCarrello = elementoEsistente ? elementoEsistente.quantita : 0;
        
        if (quantitaGiaCarrello >= prodotto.quantita) {
          this.mostraNotifica(`Non puoi aggiungere più di questo prodotto. Disponibili: ${prodotto.quantita}, già nel carrello: ${quantitaGiaCarrello}`);
        } else {
          this.store.dispatch(aggiungiAlCarrello({ prodotto: prodotto, quantita: 1 }));
          this.mostraNotifica(`${prodotto.nome} aggiunto al carrello!`);
        }
      });
    } else {
      this.mostraNotifica('Prodotto non disponibile');
    }
  }

  cambiaOrdinamento(): void {
    this.prodotti$ = this.prodotti$.pipe(
      map(prodotti => this.ordinaProdotti([...prodotti], this.ordinamento))
    );
  }

  private ordinaProdotti(prodotti: Prodotto[], tipoOrdinamento: string): Prodotto[] {
    const prodottiOrdinati = [...prodotti];
    
    switch (tipoOrdinamento) {
      case 'prezzoAsc':
        return prodottiOrdinati.sort((a, b) => a.prezzo - b.prezzo);
      case 'prezzoDesc':
        return prodottiOrdinati.sort((a, b) => b.prezzo - a.prezzo);
      case 'alfabetico':
      default:
        return prodottiOrdinati.sort((a, b) => a.nome.localeCompare(b.nome));
    }
  }

  private mostraNotifica(messaggio: string): void {
    const notifica = document.createElement('div');
    notifica.className = 'notifica-carrello';
    notifica.textContent = messaggio;
    notifica.style.cssText = `
      position: fixed;
      bottom: 20px;
      right: 20px;
      background: #28a745;
      color: white;
      padding: 12px 20px;
      border-radius: 8px;
      box-shadow: 0 4px 6px rgba(0,0,0,0.1);
      z-index: 9999;
      font-weight: 500;
      transform: translateX(400px);
      transition: transform 0.3s ease-in-out;
    `;
    
    document.body.appendChild(notifica);
    
    setTimeout(() => {
      notifica.style.transform = 'translateX(0)';
    }, 50);
    
    setTimeout(() => {
      notifica.style.transform = 'translateX(400px)';
      setTimeout(() => {
        if (document.body.contains(notifica)) {
          document.body.removeChild(notifica);
        }
      }, 300);
    }, 3000);
  }

  trackByProdotto(index: number, prodotto: Prodotto): number {
    return prodotto.id;
  }
}