package devandroid.macedo.appgaseta.view.controller;

import java.util.ArrayList;
import java.util.List;

import devandroid.macedo.appgaseta.view.model.Curso;

public class CursoController {

    private List listCursos;



    public List getListadeCursos(){

        listCursos = new ArrayList<Curso>();

        listCursos.add(new Curso("Java"));
        listCursos.add(new Curso("HTML"));
        listCursos.add(new Curso("PHP"));
        listCursos.add(new Curso("Py"));
        listCursos.add(new Curso("Kotlin"));
        listCursos.add(new Curso("JavaScript"));
        listCursos.add(new Curso("TypeScript"));
        listCursos.add(new Curso("Flutter"));


        return listCursos;

    }

    public ArrayList<String> dadosParaSpinner(){

        ArrayList<String> dados= new ArrayList<>();
        for (int i = 0; i < getListadeCursos().size(); i++) {
            Curso objeto = (Curso) getListadeCursos().get(i);
            dados.add(objeto.getNomeDoCursoDesejado());

        }
        return dados;

    }

}
