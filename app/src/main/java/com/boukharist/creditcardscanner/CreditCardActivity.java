package com.boukharist.creditcardscanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import io.card.payment.CreditCard;
import movile.com.creditcardguide.model.IssuerCode;
import movile.com.creditcardguide.view.CreditCardView;

public class CreditCardActivity extends AppCompatActivity {

    CreditCardView creditCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        creditCardView = (CreditCardView) findViewById(R.id.creditCardView);
        CreditCard creditCard = getIntent().getParcelableExtra("creditCard");

        populateCreditCard(creditCard);
    }

    void populateCreditCard(CreditCard creditCard) {

        creditCardView.setTextNumber(creditCard.getRedactedCardNumber());

        if (creditCard.getCardType() != null) {
            switch (creditCard.getCardType()) {
                case VISA:
                    creditCardView.chooseFlag(IssuerCode.VISACREDITO);
                    break;
                case MASTERCARD:
                    creditCardView.chooseFlag(IssuerCode.MASTERCARD);
                    break;
                default:
                    creditCardView.chooseFlag(IssuerCode.OTHER);
            }

        }

        if (creditCard.isExpiryValid()) {
            creditCardView.setTextExpDate(creditCard.expiryMonth + "/" + (creditCard.expiryYear - 2000));
        }

        if(creditCard.cardholderName != null){
            creditCardView.setTextLabelOwner(creditCard.cardholderName);
        }

      /*  if (creditCard.cvv != null) {
            // Never log or display a CVV
            //   creditCardView.setTextCVV(creditCard.cvv);
        }
*/
        if (creditCard.postalCode != null) {
            creditCardView.setTextCVV(creditCard.postalCode);
        }

    }

}
