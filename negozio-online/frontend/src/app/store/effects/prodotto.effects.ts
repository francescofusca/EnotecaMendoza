import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { of } from 'rxjs';
import { map, catchError, switchMap, tap } from 'rxjs/operators';
import { ProdottoService } from '../../servizi/prodotto.service';
import * as AzioniProdotti from '../actions/prodotto.actions';

@Injectable()
export class EffettiProdotti {

  caricaProdotti$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AzioniProdotti.caricaProdotti),
      tap(() => console.log('Effect: caricando prodotti...')),
      switchMap(() =>
        this.prodottoService.ottieniProdotti().pipe(
          map(prodotti => {
            console.log('Effect: prodotti caricati:', prodotti.length);
            return AzioniProdotti.caricaProdottiSuccesso({ prodotti });
          }),
          catchError(errore => {
            console.error('Effect: errore caricamento prodotti:', errore);
            return of(AzioniProdotti.caricaProdottiErrore({ errore }));
          })
        )
      )
    )
  );

  cercaProdotti$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AzioniProdotti.cercaProdotti),
      tap(action => console.log('Effect: ricerca per:', action.query)),
      switchMap(action =>
        this.prodottoService.cercaProdotti(action.query).pipe(
          map(prodotti => AzioniProdotti.cercaProdottiSuccesso({ prodotti })),
          catchError(errore => of(AzioniProdotti.caricaProdottiErrore({ errore })))
        )
      )
    )
  );
  
  aggiungiAlCarrelloLog$ = createEffect(() =>
    this.actions$.pipe(
      ofType(AzioniProdotti.aggiungiAlCarrello),
      tap(action => {
        console.log('Prodotto aggiunto al carrello:', action.prodotto.nome, 'Quantità:', action.quantita);
      })
    ), { dispatch: false }
  );

  constructor(
    private actions$: Actions,
    private prodottoService: ProdottoService
  ) {
    console.log('EffettiProdotti inizializzato');
  }
}