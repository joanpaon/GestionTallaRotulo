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
package org.japo.java.libraries;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import org.japo.java.models.Model;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesCSV {

    // Separadores
    public static final String SEPARADOR_LECTURA = "\\s*,\\s*";
    public static final String SEPARADOR_ESCRITURA = ", ";

    // Fichero CSV > Lista Items
    public static String[] importarItemsCSV(String fichero) throws Exception {
        // Lista Items (Vacio)
        String[] items;

        // Importar Items
        try (BufferedReader entrada = new BufferedReader(new FileReader(fichero))) {
            // Fichero CSV > Linea Items
            String linea = entrada.readLine();

            // Linea Items > Lista Items
            items = linea.split(SEPARADOR_LECTURA);
        }

        // Devolver Items
        return items;
    }

    // Lista Items > Fichero CSV
    public static void exportarItemsCSV(String[] items, String fichero) throws Exception {
        // Exportar Items
        try (PrintWriter salida = new PrintWriter(fichero)) {
            // Primer Item - Separado
            salida.print(items[0]);

            // Resto Items
            for (int i = 1; i < Model.NUM_ITEMS; i++) {
                salida.print(SEPARADOR_ESCRITURA + items[i]);
            }
        }
    }
}
