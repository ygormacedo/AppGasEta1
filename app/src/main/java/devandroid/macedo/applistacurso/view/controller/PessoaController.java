package devandroid.macedo.applistacurso.view.controller;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import devandroid.macedo.applistacurso.view.model.Pessoa;
import devandroid.macedo.applistacurso.view.view.MainActivity;

public class PessoaController {

    SharedPreferences preferences;
    SharedPreferences.Editor listaVip;
    public static final String NOME_PREFERENES = "pref_listaVip";

    public PessoaController(MainActivity mainActivity) {
        preferences = mainActivity.getSharedPreferences(NOME_PREFERENES, 0);
        listaVip = preferences.edit();

    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public void salvar(Pessoa pessoa) {

        Log.i("MVC_CONTROLLER", "COntroller Salva: " + pessoa.toString());

        listaVip.putString("primeiroNome", pessoa.getPrimeiroNome());
        listaVip.putString("sobreNome", pessoa.getSobreNome());
        listaVip.putString("nomeCurso", pessoa.getCursoDesejado());
        listaVip.putString("telefoneContato", pessoa.getTelefoneContato());
        listaVip.apply();

    }

    public Pessoa buscar(Pessoa pessoa) {

        pessoa.setPrimeiroNome(preferences.getString("primeiroNome", ""));
        pessoa.setSobreNome(preferences.getString("sobreNome", ""));
        pessoa.setCursoDesejado(preferences.getString("nomeCurso", ""));
        pessoa.setTelefoneContato(preferences.getString("telefoneContato", ""));

        return pessoa;

    }

    public void limpar(Pessoa pessoa){
        listaVip.clear();
        listaVip.apply();
    }
}
