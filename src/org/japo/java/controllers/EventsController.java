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

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import org.japo.java.entities.Model;
import org.japo.java.forms.View;
import org.japo.java.interfaces.IDAController;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class EventsController {

    // Referencias
    private final Controller control;
    private final Model model;
    private final View view;
    private final Properties prpApp;
    private final IDAController daControl;

    // Constructor Parametrizado
    public EventsController(Controller control) {
        this.control = control;
        model = control.getModel();
        view = control.getView();
        prpApp = control.getPrpApp();
        daControl = control.getDaControl();
    }

    // Evento de Ventana - Cerrando
    public void procesarCierreVentana(WindowEvent e) {
        // Memorizar Estado Actual
        control.memorizarEstadoApp();

        // Otros Procesos de Cierre    
    }

    // Evento de Acción - Cargar Datos
    public void procesarImportacion(ActionEvent evt) {
        try {
            // Fichero de Datos
            String fichero = prpApp.getProperty("fichero_datos");

            // Persistencia > Modelo
            daControl.importarModelo(model, fichero);

            // Modelo > Interfaz
            control.sincronizarModeloVista();

            // Validar Datos Cargados > Interfaz
            control.comprobarValidez();

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
        if (control.comprobarValidez()) {
            try {
                // Interfaz > Modelo
                control.sincronizarVistaModelo();

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

    public void procesarSeleccionTipografia() {
//        // Interfaz > modelo
//        model.setFamilia((String) view.cbbFamilia.getSelectedItem());
//
//        // Modelo > Interfaz
//        control.sincronizarModeloInterfaz();
    }

    // Procesar cambios de un Campo de Texto
    public void procesarEventosDocumento(DocumentEvent e) {
//        // Campo de Texto > Modelo
//        model.setTexto(view.txfTexto.getText());
//
//        // Campo de Texto > Etiqueta
//        view.lblRotulo.setText(view.txfTexto.getText());
    }

    // Cambiar Frente/Fondo
    public void procesarCambioPlano(ActionEvent evt) {
        // Modelo > Vista
        control.sincronizarModeloVista();
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
//        // Modelo > Interfaz
//        control.sincronizarModeloVista();
    }

    public void procesarNegrita(ActionEvent evt) {
//        // Vista > Modelo
//        model.setNegrita(view.cbxNegrita.isSelected());
//        
//        // Modelo > Interfaz
//        control.sincronizarModeloVista();
    }

    public void procesarCursiva(ActionEvent evt) {
//        // Vista > Modelo
//        model.setCursiva(view.cbxCursiva.isSelected());
//        
//        // Modelo > Interfaz
//        control.sincronizarModeloVista();
    }

    public void procesarTalla(ChangeEvent evt) {
        // Vista > modelo
        if (evt.getSource().equals(view.sldTalla)) {
            model.setTalla(view.sldTalla.getValue());
        } else {
            model.setTalla((int) view.spnTalla.getValue());
        }

        // Modelo > Vista
        control.sincronizarModeloVista();
    }
}
