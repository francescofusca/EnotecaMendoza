import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { map, take } from 'rxjs/operators';
import { Prodotto } from '../modelli/prodotto.model';
import { AppState } from '../store/app.state';
import { caricaProdotti, aggiungiAlCarrello } from '../store/actions/prodotto.actions';
import { selezionaTuttiProdotti, selezionaCaricamentoProdotti, selezionaCarrello } from '../store/selectors/prodotto.selectors';
import { ProdottoService } from '../servizi/prodotto.service';

export interface CategoriaRaggruppata {
  nome: string;
  id: number;
  prodotti: Prodotto[];
}

@Component({
  selector: 'app-prodotti',
  templateUrl: './prodotti.component.html',
  styleUrls: ['./prodotti.component.css']
})
export class ProdottiComponent implements OnInit {
  
  categorieRaggruppate$!: Observable<CategoriaRaggruppata[]>;
  risultatiRicerca: Prodotto[] = [];
  ricercaAttiva = false;

  loading$: Observable<boolean>;
  testoRicerca: string = '';
  private readonly backendUrl = 'http://localhost:8080';
  
  constructor(private store: Store<AppState>, private prodottoService: ProdottoService) {
    this.loading$ = this.store.select(selezionaCaricamentoProdotti);
  }

  ngOnInit(): void {
    this.store.dispatch(caricaProdotti());
    const prodotti$ = this.store.select(selezionaTuttiProdotti);

    this.categorieRaggruppate$ = prodotti$.pipe(
      map(prodotti => {
        if (!prodotti || prodotti.length === 0) return [];
        const mappaCategorie: { [key: number]: string } = {
          1: 'Vini Rossi', 2: 'Vini Bianchi', 3: 'Vini Rosati',
          4: 'Vini da 375 ml', 5: 'Vini Spumanti', 6: 'Vini Dolci'
        };
        const prodottiRaggruppati = prodotti.reduce((acc, prodotto) => {
          const categoriaId = prodotto.categoriaId;
          if (!acc[categoriaId]) acc[categoriaId] = [];
          acc[categoriaId].push(prodotto);
          return acc;
        }, {} as { [key: number]: Prodotto[] });
        return Object.keys(mappaCategorie).map(idString => {
          const id = Number(idString);
          return {
            id: id,
            nome: mappaCategorie[id],
            prodotti: (prodottiRaggruppati[id] || []).slice(0, 8)
          };
        }).filter(gruppo => gruppo.prodotti.length > 0);
      })
    );
  }
  
  cerca(): void {
    const termine = this.testoRicerca.trim();
    if (!termine) {
      this.resetRicerca();
      return;
    }
    this.ricercaAttiva = true;
    this.prodottoService.cercaProdotti(termine).subscribe({
      next: (risultati) => {
        this.risultatiRicerca = risultati;
      },
      error: (errore) => {
        console.error('Errore nella ricerca:', errore);
        this.risultatiRicerca = [];
      }
    });
  }

  resetRicerca(): void {
    this.testoRicerca = '';
    this.ricercaAttiva = false;
    this.risultatiRicerca = [];
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
    // Creo elemento notifica
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
    
    // Animazione di entrata
    setTimeout(() => {
      notifica.style.transform = 'translateX(0)';
    }, 50);
    
    // Rimozione dopo 3 secondi
    setTimeout(() => {
      notifica.style.transform = 'translateX(400px)';
      setTimeout(() => {
        if (document.body.contains(notifica)) {
          document.body.removeChild(notifica);
        }
      }, 300);
    }, 3000);
  }
  
  getUrlImmagine(prodotto: Prodotto): string {
    if (!prodotto.urlImmagine) return 'assets/immagini/no-image.png'; 
    if (prodotto.urlImmagine.startsWith('http')) return prodotto.urlImmagine;
    return `${this.backendUrl}${prodotto.urlImmagine}`;
  }
  
  trackByProdotto(_: number, prodotto: Prodotto): number {
    return prodotto.id;
  }
}