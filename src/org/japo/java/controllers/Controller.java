/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.controllers;

import java.awt.Color;
import java.awt.Font;
import java.util.Properties;
import org.japo.java.entities.Model;
import org.japo.java.forms.View;
import org.japo.java.lib.UtilesApp;
import org.japo.java.lib.UtilesSwing;
import org.japo.java.interfaces.IDAController;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class Controller {

    // Referencias
    private final View view;
    private final Properties prpApp;
    private final Model model;
    private final ModelController modelControl;
    private final IDAController daControl;
    private final EventsController eventsControl;

    // Constructor Parametrizado
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Propiedades App
        this.prpApp = UtilesApp.cargarPropiedades();

        // Controlador de Modelo
        this.modelControl = new ModelController(this);

        // *** Controlador de Persistencia ***
        this.daControl = new DAControllerSXML(modelControl);

        // Controlador de Eventos
        this.eventsControl = new EventsController(this);
    }

    public View getView() {
        return view;
    }

    public Properties getPrpApp() {
        return prpApp;
    }

    public Model getModel() {
        return model;
    }

    public ModelController getModelControl() {
        return modelControl;
    }

    public IDAController getDaControl() {
        return daControl;
    }

    public EventsController getEventsControl() {
        return eventsControl;
    }

    // Estado Actual > Persistencia
    public void memorizarEstadoApp() {
        // Actualiza Propiedades Estado Actual

        // Guardar Estado Actual
        // UtilesApp.guardarPropiedades(prpApp);
    }

    // Interfaz (Subjetivo) > Modelo
    public void sincronizarVistaModelo() {
//        // Campo de Texto - Item 
//        if (UtilesValidacion.validarDato(
//            view.txfItem.getText(), Model.ER_ITEM)) {
//            model.setItem(txfItem.getText());
//        } else {
//            model.setItem(Model.DEF_ITEM);
//        }
    }

    // Modelo > Interfaz 
    public void sincronizarModeloVista() {
        // Texto
        view.lblRotulo.setText(model.getTexto());

        // Estilo
//        view.cbxNegrita.setSelected(model.isNegrita());
//        view.cbxCursiva.setSelected(model.isCursiva());
        // Talla
        UtilesSwing.ajustarValorDeslizador(view.sldTalla, model.getTalla());
        UtilesSwing.ajustarValorCambiador(view.spnTalla, model.getTalla());

        // Fuente
        String familia = model.getFamilia();
        int negrita = model.isNegrita() ? Font.BOLD : Font.PLAIN;
        int cursiva = model.isCursiva() ? Font.ITALIC : Font.PLAIN;
        int talla = model.getTalla();
        view.lblRotulo.setFont(new Font(familia, negrita + cursiva, talla));

        // Frente
        int frenteR = model.getFrenteR();
        int frenteV = model.getFrenteV();
        int frenteA = model.getFrenteA();
        Color frente = new Color(frenteR, frenteV, frenteA);
        view.lblRotulo.setForeground(frente);
//        view.lblFrente.setBackground(frente);

        // Fondo
        int fondoR = model.getFondoR();
        int fondoV = model.getFondoV();
        int fondoA = model.getFondoA();
        Color fondo = new Color(fondoR, fondoV, fondoA);
        view.lblRotulo.setBackground(fondo);
//        view.lblFondo.setBackground(fondo);

//        // Analisis selección Frente/Fondo
//        if (view.rbtFrente.isSelected()) {
//            // Modelo > ROJO - Frente
//            UtilesSwing.ajustarValorDeslizador(view.sldRojo, frenteR);
//            view.lblRojo.setText(frenteR + "");
//
//            // Modelo > VERDE - Frente
//            UtilesSwing.ajustarValorDeslizador(view.sldVerde, frenteV);
//            view.lblVerde.setText(frenteV + "");
//
//            // Modelo > AZUL - Frente
//            UtilesSwing.ajustarValorDeslizador(view.sldAzul, frenteA);
//            view.lblAzul.setText(frenteA + "");
//        } else {
//            // Modelo > ROJO - Fondo
//            UtilesSwing.ajustarValorDeslizador(view.sldRojo, fondoR);
//            view.lblRojo.setText(fondoR + "");
//
//            // Modelo > VERDE - Fondo
//            UtilesSwing.ajustarValorDeslizador(view.sldVerde, fondoV);
//            view.lblVerde.setText(fondoV + "");
//
//            // Modelo > AZUL - Fondo
//            UtilesSwing.ajustarValorDeslizador(view.sldAzul, fondoA);
//            view.lblAzul.setText(fondoA + "");
//        }
    }

    // Validar Controles Subjetivos
    public boolean comprobarValidez() {
        // Validación Individual
//        boolean item1OK = UtilesValidacion.validarCampoTexto(txfItem1, Modelo.ER_ITEM1, "*");
//        boolean item2OK = UtilesValidacion.validarCampoTexto(txfItem2, Modelo.ER_ITEM2, "*");
//        boolean item4OK = UtilesValidacion.validarCampoFecha(txfItem4, "*");
//        boolean item5OK = UtilesValidacion.validarCampoTexto(txfItem5, Modelo.ER_ITEM5, "*");

        // Validación Conjunta
//        return item1OK && item2OK && item4OK && item5OK;
        return true;
    }

    // Persistencia > Estado Actual
    public void restaurarEstadoApp() {
        // Establece Lnf
        UtilesSwing.establecerLnF(getPrpApp().getProperty("lnf", UtilesSwing.WINDOWS));

        // Activa Singleton
        if (!UtilesApp.activarInstancia(prpApp.getProperty("puerto_bloqueo", UtilesApp.PUERTO_BLOQUEO))) {
            UtilesSwing.terminarPrograma(view);
        }

        // Activa otras propiedades
    }

}
