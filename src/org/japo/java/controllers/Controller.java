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
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import org.japo.java.entities.Model;
import org.japo.java.view.View;
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

    // Constructor Parametrizado
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Propiedades App
        this.prpApp = UtilesApp.cargarPropiedades();

        // Controlador de Modelo
        this.modelControl = new ModelController();

        // *** Controlador de Persistencia ***
        this.daControl = new DAControllerSXML(modelControl);
    }

    // --- INICIO SETTERS / GETTERS ----
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

    // --- FIN SETTERS / GETTERS ----
    //
    // Persistencia > Estado Actual
    public void restaurarEstadoApp() {
        // Establece Lnf
        UtilesSwing.establecerLnF(prpApp.getProperty("lnf", UtilesSwing.WINDOWS));

        // Activa Singleton
        if (!UtilesApp.activarInstancia(prpApp.getProperty("puerto_bloqueo", UtilesApp.PUERTO_BLOQUEO))) {
            UtilesSwing.terminarPrograma(view);
        }

        // Activa otras propiedades
    }

    // Interfaz (Subjetivo) > Modelo
    public void sincronizarVistaModelo(Model model, View view) {
//        // Campo de Texto - Item 
//        if (UtilesValidacion.validarDato(
//            view.txfItem.getText(), Model.ER_ITEM)) {
//            model.setItem(txfItem.getText());
//        } else {
//            model.setItem(Model.DEF_ITEM);
//        }
    }

    // Modelo > Interfaz 
    public void sincronizarModeloVista(Model model, View view) {
        // Texto
        view.lblRotulo.setText(model.getTexto());
//        view.txfTexto.setText(model.getTexto());

//        // Tipografia
//        UtilesSwing.seleccionarElementoCombo(view.cbbFamilia, model.getFamilia());
//
//        // Estilo
//        view.cbxNegrita.setSelected(model.isNegrita());
//        view.cbxCursiva.setSelected(model.isCursiva());
//
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

    // Iniciado Cierre Ventana
    public void procesarCierreVentana(WindowEvent evt) {
        // Memorizar Estado de la Applicación
        modelControl.memorizarEstadoApp(prpApp);
    }

    // Persistencia > Modelo > Interfaz
    public void procesarImportacion(ActionEvent evt) {
        try {
            // Fichero de Datos
            String fichero = prpApp.getProperty("fichero_datos");

            // Persistencia > Modelo
            daControl.importarModelo(model, fichero);

            // Modelo > Interfaz
            sincronizarModeloVista(model, view);

            // Validar Datos Cargados > Interfaz
            modelControl.comprobarValidez(view);

            // Mensaje - Importación OK
            String msg = "Datos cargados correctamente";
            JOptionPane.showMessageDialog(view, msg);
        } catch (Exception e) {
            // Mensaje - Importación NO
            String msg = "Error al cargar los datos";
            JOptionPane.showMessageDialog(view, msg);
        }
    }

    // Interfaz > Modelo > Persistencia
    public void procesarExportacion(ActionEvent evt) {
        // Validar Datos Interfaz
        if (modelControl.comprobarValidez(view)) {
            try {
                // Interfaz > Modelo
                sincronizarVistaModelo(model, view);

                // Fichero de Datos
                String fichero = prpApp.getProperty("fichero_datos");

                // Modelo > Persistencia
                daControl.exportarModelo(model, fichero);

                // Mensaje - Exportación OK
                String msg = "Datos guardados correctamente";
                JOptionPane.showMessageDialog(view, msg);
            } catch (Exception e) {
                // Mensaje - Exportación NO
                String msg = "Error al guardar los datos";
                JOptionPane.showMessageDialog(view, msg);
            }
        } else {
            // Mensaje - Validación Pendiente
            JOptionPane.showMessageDialog(view, "Hay datos erróneos.");
        }
    }

    // Procesar cambio de Texto
    public void procesarCambioTexto(DocumentEvent e) {
//        // Campo de Texto > Modelo
//        model.setTexto(view.txfTexto.getText());
//
//        // Campo de Texto > Etiqueta
//        view.lblRotulo.setText(view.txfTexto.getText());
    }

    // Cambiar Familia Tipográfica
    public void procesarCambioTipografia(ActionEvent evt) {
//        // Vista > Modelo
//        model.setFamilia((String) view.cbbFamilia.getSelectedItem());
//
//        // Modelo > Vista
//        sincronizarModeloVista(model, view);
    }

    // Activar/Desactivar Estilo Negrita
    public void procesarNegrita(ActionEvent evt) {
//        // Vista > Modelo
//        model.setNegrita(view.cbxNegrita.isSelected());
//        
//        // Modelo > Vista
//        sincronizarModeloVista(model, view);
    }

    // Activar/Desactivar Estilo Cursiva
    public void procesarCursiva(ActionEvent evt) {
//        // Vista > Modelo
//        model.setCursiva(view.cbxCursiva.isSelected());
//
//        // Modelo > Vista
//        sincronizarModeloVista(model, view);
    }

    // Cambiar Tamaño de Fuente
    public void procesarTalla(ChangeEvent evt) {
        // Vista > Modelo
        if (evt.getSource().equals(view.sldTalla)) {
            model.setTalla(view.sldTalla.getValue());
        } else {
            model.setTalla((int) view.spnTalla.getValue());
        }

        // Modelo > Vista
        sincronizarModeloVista(model, view);
    }

    // Cambiar Componente Color
    public void procesarAjusteColor(ChangeEvent evt) {
//        // Vista > Modelo
//        if (view.rbtFrente.isSelected()) {
//            if (evt.getSource().equals(view.sldRojo)) {
//                model.setFrenteR(view.sldRojo.getValue());
//            } else if (evt.getSource().equals(view.sldVerde)) {
//                model.setFrenteV(view.sldVerde.getValue());
//            } else {
//                model.setFrenteA(view.sldAzul.getValue());
//            }
//        } else {
//            if (evt.getSource().equals(view.sldRojo)) {
//                model.setFondoR(view.sldRojo.getValue());
//            } else if (evt.getSource().equals(view.sldVerde)) {
//                model.setFondoV(view.sldVerde.getValue());
//            } else {
//                model.setFondoA(view.sldAzul.getValue());
//            }
//        }
//
//        // Modelo > Vista
//        sincronizarModeloVista(model, view);
    }

    // Cambiar Frente/Fondo
    public void procesarCambioFrenteFondo(ActionEvent evt) {
        // Modelo > Vista
        sincronizarModeloVista(model, view);
    }
}
