package devandroid.macedo.appgaseta.view.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import devandroid.macedo.appgaseta.R;
import devandroid.macedo.appgaseta.view.commons.UtilGasEta;
import devandroid.macedo.appgaseta.view.controller.CombustivelController;
import devandroid.macedo.appgaseta.view.model.Combustivel;

public class GasEstaActivity extends AppCompatActivity {

    CombustivelController controller;

    Combustivel combustivelGasolina;
    Combustivel combustivelEtanol;

    EditText editGasolina;
    EditText editEtanol;

    TextView txtResultado;

    Button btnCalcular;
    Button btnLimpar;
    Button btnSalvar;
    Button btnFinalizar;

    double precoGasolina;
    double precoEtanol;
    String recomendacao;

    List<Combustivel> dados;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gaseta);

        controller = new CombustivelController(GasEstaActivity.this);

        // Obtenha a lista de dados do controlador
        dados = controller.getListaDeDados();

        // Verifique se a lista contém dois ou mais elementos antes de acessar
        if (dados.size() > 1) {
            Combustivel objAlteracao = dados.get(1);

            objAlteracao.setNomeDoCombustivel("**GASOLINA**");
            objAlteracao.setPrecoDoCombustivel(5.97);
            objAlteracao.setRecomendacao("**Abastecer com Gasolina**");

            // controller.alterar(objAlteracao);
        } else {
            // Tratamento para o caso em que a lista tem menos de 2 elementos
            Toast.makeText(GasEstaActivity.this, "Não há dados suficientes na lista.", Toast.LENGTH_LONG).show();
        }

        // controller.deletar(50); // Certifique-se de que o item a ser deletado existe

        editGasolina = findViewById(R.id.editGasolina);
        editEtanol = findViewById(R.id.editEtanol);

        txtResultado = findViewById(R.id.txtResultado);

        btnCalcular = findViewById(R.id.btnCalculate);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isDadosOk = true;

                if (TextUtils.isEmpty(editGasolina.getText())) {
                    editGasolina.setError("* Obrigatório");
                    editGasolina.requestFocus();
                    isDadosOk = false;
                }

                if (TextUtils.isEmpty(editEtanol.getText())) {
                    editEtanol.setError("* Obrigatório");
                    editEtanol.requestFocus();
                    isDadosOk = false;
                }

                if (isDadosOk) {

                    precoGasolina = Double.parseDouble(editGasolina.getText().toString());
                    precoEtanol = Double.parseDouble(editEtanol.getText().toString());

                    recomendacao = UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol);

                    txtResultado.setText(recomendacao);

                    btnSalvar.setEnabled(true);
                } else {
                    Toast.makeText(GasEstaActivity.this,
                            "Por favor, digite os dados obrigatórios...",
                            Toast.LENGTH_LONG).show();
                    btnSalvar.setEnabled(false);
                }
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                combustivelGasolina = new Combustivel();
                combustivelEtanol = new Combustivel();

                combustivelGasolina.setNomeDoCombustivel("Gasolina");
                combustivelGasolina.setPrecoDoCombustivel(precoGasolina);

                combustivelEtanol.setNomeDoCombustivel("Etanol");
                combustivelEtanol.setPrecoDoCombustivel(precoEtanol);

                combustivelGasolina.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));
                combustivelEtanol.setRecomendacao(UtilGasEta.calcularMelhorOpcao(precoGasolina, precoEtanol));

                controller.salvar(combustivelGasolina);
                controller.salvar(combustivelEtanol);
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEtanol.setText("");
                editGasolina.setText("");

                btnSalvar.setEnabled(false);

                controller.limpar();
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GasEstaActivity.this, "GasEta bom economia...",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        });

        Toast.makeText(GasEstaActivity.this,
                UtilGasEta.calcularMelhorOpcao(5.12, 3.39),
                Toast.LENGTH_LONG).show();
    }
}
