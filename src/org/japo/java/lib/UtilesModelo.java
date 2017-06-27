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
package org.japo.java.lib;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import org.japo.java.entities.Modelo;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class UtilesModelo {

    // Archivo [CSV] > Modelo
    public static void importarModeloCSV(
        String archivo, String separador, Modelo modelo)
        throws Exception {
        // Lectura de un fichero de texto
        try (BufferedReader br = new BufferedReader(
                            new FileReader(archivo))) {
            // Linea de texto a leer
            String linea = br.readLine();

            // Fichero > Items
            String[] items = linea.split(separador);

            // Items > Modelo
            modelo.asignarItemsModelo(items);
        }
    }

    // Archivo [Serialización Binaria] > Modelo
    public static void importarModeloSBIN(
        String archivo, Modelo modeloFin) throws Exception {
        try (ObjectInputStream entrada = new ObjectInputStream(
                               new FileInputStream(archivo))) {
            // Persistencia Binaria > Modelo Temporal
            Modelo modeloIni = (Modelo) entrada.readObject();

            // Modelo Temporal > Modelo
            modeloFin.copiarModelo(modeloIni);
        }
    }

    // Archivo [Serialización XML] > Modelo
    public static void importarModeloXML(
        String ficheroDatos, Modelo modeloFin) throws Exception {
        try (XMLDecoder entrada = new XMLDecoder(
                        new FileInputStream(ficheroDatos))) {
            // Persistencia Binaria > Modelo Temporal            
            Modelo modeloIni = (Modelo) entrada.readObject();

            // Modelo Temporal > Modelo
            modeloFin.copiarModelo(modeloIni);
        }
    }

    // Modelo > Archivo [CSV]
    public static void exportarModeloCSV(
        String archivo, String separador, Modelo modelo)
        throws Exception {
        // Lectura de un fichero de texto
        try (PrintWriter pw = new PrintWriter(
                         new FileWriter(archivo))) {
            // Modelo > Items
            String[] items = modelo.asignarModeloItems();

            // Escribe el primer item por separado
            pw.print(items[0]);

            // Escribe el resto de los items
            for (int i = 1; i < items.length; i++) {
                pw.print(separador + items[i]);
            }
        }
    }

    // Modelo > Archivo [Serialización Binaria]
    public static void exportarModeloSBIN(
        String ficheroDatos, Modelo modelo) throws Exception {
        try (ObjectOutputStream entrada = new ObjectOutputStream(
                                new FileOutputStream(ficheroDatos))) {
            // Escribe el modelo
            entrada.writeObject(modelo);
        }
    }

    // Modelo > Archivo [Serialización XML]
    public static void exportarModeloXML(
        String ficheroDatos, Modelo modelo) throws Exception {
        try (XMLEncoder salida = new XMLEncoder(
                        new FileOutputStream(ficheroDatos))) {
            // Escribe el modelo
            salida.writeObject(modelo);
        }
    }

}
