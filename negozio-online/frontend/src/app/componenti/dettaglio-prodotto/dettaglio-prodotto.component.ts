import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProdottoService } from '../../servizi/prodotto.service';
import { Prodotto } from '../../modelli/prodotto.model';
import { Observable } from 'rxjs';
import { switchMap, take } from 'rxjs/operators';
import { Store } from '@ngrx/store';
import { AppState } from '../../store/app.state';
import { aggiungiAlCarrello } from '../../store/actions/prodotto.actions';
import { selezionaCarrello } from '../../store/selectors/prodotto.selectors';

@Component({
  selector: 'app-dettaglio-prodotto',
  templateUrl: './dettaglio-prodotto.component.html',
  styleUrls: ['./dettaglio-prodotto.component.css']
})
export class DettaglioProdottoComponent implements OnInit {

  prodotto$!: Observable<Prodotto>;
  private readonly backendUrl = 'http://localhost:8080';

  constructor(
    private route: ActivatedRoute,
    private prodottoService: ProdottoService,
    private store: Store<AppState>
  ) { }

  ngOnInit(): void {
    this.prodotto$ = this.route.paramMap.pipe(
      switchMap(params => {
        const id = Number(params.get('id'));
        return this.prodottoService.ottieniProdotto(id);
      })
    );
  }

  getUrlImmagine(prodotto: Prodotto): string {
    if (!prodotto.urlImmagine) return 'assets/immagini/no-image.png';
    if (prodotto.urlImmagine.startsWith('http')) return prodotto.urlImmagine;
    return `${this.backendUrl}${prodotto.urlImmagine}`;
  }

  aggiungiAlCarrello(prodotto: Prodotto): void {
    if (prodotto.quantita > 0) {
      // Controllo se il prodotto è già nel carrello
      this.store.select(selezionaCarrello).pipe(take(1)).subscribe(carrello => {
        const elementoEsistente = carrello.find(elem => elem.prodotto.id === prodotto.id);
        const quantitaGiaCarrello = elementoEsistente ? elementoEsistente.quantita : 0;
        
        if (quantitaGiaCarrello >= prodotto.quantita) {
          this.mostraNotifica(`Non puoi aggiungere più di questo prodotto. Disponibili: ${prodotto.quantita}, già nel carrello: ${quantitaGiaCarrello}`);
        } else {
          this.store.dispatch(aggiungiAlCarrello({ prodotto, quantita: 1 }));
          this.mostraNotifica(`${prodotto.nome} aggiunto al carrello!`);
        }
      });
    } else {
      this.mostraNotifica('Prodotto non disponibile');
    }
  }

  isProdottoDisponibile(prodotto: Prodotto): boolean {
    return prodotto.quantita > 0;
  }

  getTestoDisponibilita(prodotto: Prodotto): string {
    if (prodotto.quantita === 0) {
      return 'Non disponibile';
    } else if (prodotto.quantita <= 3) {
      return `Solo ${prodotto.quantita} rimaste`;
    } else {
      return 'Disponibile';
    }
  }

  getClasseDisponibilita(prodotto: Prodotto): string {
    if (prodotto.quantita === 0) {
      return 'non-disponibile';
    } else if (prodotto.quantita <= 3) {
      return 'scorte-basse';
    } else {
      return 'disponibile';
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
}