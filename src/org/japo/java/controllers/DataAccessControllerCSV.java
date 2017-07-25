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

import org.japo.java.models.Model;
import org.japo.java.interfaces.IDataAccessController;
import org.japo.java.libraries.UtilesCSV;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class DataAccessControllerCSV implements IDataAccessController {

    // Fichero CSV > Modelo
    @Override
    public void importarModelo(Model model, String fichero) throws Exception {
        // Fichero CSV > Lista Items
        String[] items = UtilesCSV.importarItemsCSV(fichero);

        // Lista Items > Modelo
        convertirArrayModelo(items, model);        
    }

    // Modelo > Fichero CSV
    @Override
    public void exportarModelo(Model model, String fichero) throws Exception {
        // Lista Items
        String[] items = new String[Model.NUM_ITEMS];

        // Modelo > Lista Items
        convertirModeloArray(model, items);

        // Lista Items > Fichero CSV
        UtilesCSV.exportarItemsCSV(items, fichero);
    }

    // Modelo > Lista Items
    public void convertirModeloArray(Model model, String[] items) {
        items[Model.POS_TEXTO] = model.getTexto();
        items[Model.POS_FAMILIA] = model.getFamilia();
        items[Model.POS_NEGRITA] = model.isNegrita() + "";
        items[Model.POS_CURSIVA] = model.isCursiva() + "";
        items[Model.POS_TALLA] = model.getTalla() + "";
        items[Model.POS_FRENTE_R] = model.getFrenteR() + "";
        items[Model.POS_FRENTE_V] = model.getFrenteV() + "";
        items[Model.POS_FRENTE_A] = model.getFrenteA() + "";
        items[Model.POS_FONDO_R] = model.getFondoR() + "";
        items[Model.POS_FONDO_V] = model.getFondoV() + "";
        items[Model.POS_FONDO_A] = model.getFondoA() + "";
    }

    // Lista Items > Modelo
    public void convertirArrayModelo(String[] items, Model model) {
        // Texto
        model.setTexto(items[Model.POS_TEXTO]);

        // Familia
        model.setFamilia(items[Model.POS_FAMILIA]);

        // Estilo
        model.setNegrita(items[Model.POS_NEGRITA].equals("true"));
        model.setCursiva(items[Model.POS_CURSIVA].equals("true"));

        // Talla
        try {
            model.setTalla(Integer.parseInt(items[Model.POS_TALLA]));
        } catch (NumberFormatException e) {
            model.setTalla(Model.DEF_TALLA);
        }

        // Frente
        try {
            model.setFrenteR(Integer.parseInt(items[Model.POS_FRENTE_R]));
            model.setFrenteV(Integer.parseInt(items[Model.POS_FRENTE_V]));
            model.setFrenteA(Integer.parseInt(items[Model.POS_FRENTE_A]));
        } catch (NumberFormatException e) {
            model.setFrenteR(Model.DEF_FRENTE_R);
            model.setFrenteV(Model.DEF_FRENTE_V);
            model.setFrenteA(Model.DEF_FRENTE_A);
        }

        // Fondo
        try {
            model.setFondoR(Integer.parseInt(items[Model.POS_FONDO_R]));
            model.setFondoV(Integer.parseInt(items[Model.POS_FONDO_V]));
            model.setFondoA(Integer.parseInt(items[Model.POS_FONDO_A]));
        } catch (NumberFormatException e) {
            model.setFondoR(Model.DEF_FONDO_R);
            model.setFondoV(Model.DEF_FONDO_V);
            model.setFondoA(Model.DEF_FONDO_A);
        }
    }
}
