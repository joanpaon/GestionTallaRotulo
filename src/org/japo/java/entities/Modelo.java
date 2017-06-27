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
package org.japo.java.entities;

import java.io.Serializable;
import org.japo.java.lib.UtilesValidacion;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class Modelo implements Serializable {

    // Número de items
    public static final int NUM_ITEMS = 11;

    // Constantes de acceso
    public static final int POS_TEXTO = 0;
    public static final int POS_FAMILIA = 1;
    public static final int POS_NEGRITA = 2;
    public static final int POS_CURSIVA = 3;
    public static final int POS_TALLA = 4;
    public static final int POS_FRENTE_R = 5;
    public static final int POS_FRENTE_V = 6;
    public static final int POS_FRENTE_A = 7;
    public static final int POS_FONDO_R = 8;
    public static final int POS_FONDO_V = 9;
    public static final int POS_FONDO_A = 10;

    // Expresiones regulares
    public static final String ER_TEXTO = "[\\wáéíóúüñÁÉÍÓÚÜÑ ]+";
    public static final String ER_FAMILIA = "[\\w ]+";
    public static final String ER_NEGRITA = "true|false";
    public static final String ER_CURSIVA = "true|false";
    public static final String ER_TALLA = "[1-9]\\d|\\d|100";
    public static final String ER_FRENTE_R = "25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d";
    public static final String ER_FRENTE_V = "25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d";
    public static final String ER_FRENTE_A = "25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d";
    public static final String ER_FONDO_R = "25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d";
    public static final String ER_FONDO_V = "25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d";
    public static final String ER_FONDO_A = "25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d";

    // Valores por defecto
    public static final String DEF_TEXTO = "Texto de Prueba";
    public static final String DEF_FAMILIA = "Calibri";
    public static final boolean DEF_NEGRITA = false;
    public static final boolean DEF_CURSIVA = false;
    public static final int DEF_TALLA = 50;
    public static final int DEF_FRENTE_R = 0;
    public static final int DEF_FRENTE_V = 0;
    public static final int DEF_FRENTE_A = 0;
    public static final int DEF_FONDO_R = 255;
    public static final int DEF_FONDO_V = 255;
    public static final int DEF_FONDO_A = 255;

    // Campos de la entidad
    private String texto;
    private String familia;
    private boolean negrita;
    private boolean cursiva;
    private int talla;
    private int frenteR;
    private int frenteV;
    private int frenteA;
    private int fondoR;
    private int fondoV;
    private int fondoA;

    // Constructor Predeterminado
    public Modelo() {
        texto = DEF_TEXTO;
        familia = DEF_FAMILIA;
        negrita = DEF_NEGRITA;
        cursiva = DEF_CURSIVA;
        talla = DEF_TALLA;
        frenteR = DEF_FRENTE_R;
        frenteV = DEF_FRENTE_V;
        frenteA = DEF_FRENTE_A;
        fondoR = DEF_FONDO_R;
        fondoV = DEF_FONDO_V;
        fondoA = DEF_FONDO_A;
    }

    // Constructor Parametrizado
    public Modelo(String texto, String familia,
                  boolean negrita, boolean cursiva, int talla,
                  int frenteR, int frenteV, int frenteA,
                  int fondoR, int fondoV, int fondoA) {
        // Texto
        if (UtilesValidacion.validarDato(texto, ER_TEXTO)) {
            this.texto = texto;
        } else {
            this.texto = DEF_TEXTO;
        }

        // Familia
        if (UtilesValidacion.validarDato(familia, ER_FAMILIA)) {
            this.familia = familia;
        } else {
            this.familia = DEF_FAMILIA;
        }

        // Negrita
        this.negrita = negrita;

        // Cursiva
        this.cursiva = cursiva;

        // Talla
        if (UtilesValidacion.validarDato(talla + "", ER_TALLA)) {
            this.talla = talla;
        } else {
            this.talla = DEF_TALLA;
        }

        // Frente R
        if (UtilesValidacion.validarDato(frenteR + "", ER_FRENTE_R)) {
            this.frenteR = frenteR;
        } else {
            this.frenteR = DEF_FRENTE_R;
        }

        // Frente V
        if (UtilesValidacion.validarDato(frenteV + "", ER_FRENTE_V)) {
            this.frenteV = frenteV;
        } else {
            this.frenteV = DEF_FRENTE_V;
        }

        // Frente A
        if (UtilesValidacion.validarDato(frenteA + "", ER_FRENTE_A)) {
            this.frenteA = frenteA;
        } else {
            this.frenteA = DEF_FRENTE_A;
        }

        // Fondo R
        if (UtilesValidacion.validarDato(fondoR + "", ER_FONDO_R)) {
            this.fondoR = fondoR;
        } else {
            this.fondoR = DEF_FONDO_R;
        }

        // Fondo V
        if (UtilesValidacion.validarDato(fondoV + "", ER_FONDO_V)) {
            this.fondoV = fondoV;
        } else {
            this.fondoV = DEF_FONDO_V;
        }

        // Fondo A
        if (UtilesValidacion.validarDato(fondoA + "", ER_FONDO_A)) {
            this.fondoA = fondoA;
        } else {
            this.fondoA = DEF_FONDO_A;
        }

    }

    // --- INICIO SETTERS / GETTERS
    //
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        if (UtilesValidacion.validarDato(texto, ER_TEXTO)) {
            this.texto = texto;
        }
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        if (UtilesValidacion.validarDato(familia, ER_FAMILIA)) {
            this.familia = familia;
        }
    }

    public boolean isNegrita() {
        return negrita;
    }

    public void setNegrita(boolean negrita) {
        this.negrita = negrita;
    }

    public boolean isCursiva() {
        return cursiva;
    }

    public void setCursiva(boolean cursiva) {
        this.cursiva = cursiva;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        if (UtilesValidacion.validarDato(talla + "", ER_TALLA)) {
            this.talla = talla;
        }
    }

    public int getFrenteR() {
        return frenteR;
    }

    public void setFrenteR(int frenteR) {
        if (UtilesValidacion.validarDato(frenteR + "", ER_FRENTE_R)) {
            this.frenteR = frenteR;
        }
    }

    public int getFrenteV() {
        return frenteV;
    }

    public void setFrenteV(int frenteV) {
        if (UtilesValidacion.validarDato(frenteV + "", ER_FRENTE_V)) {
            this.frenteV = frenteV;
        }
    }

    public int getFrenteA() {
        return frenteA;
    }

    public void setFrenteA(int frenteA) {
        if (UtilesValidacion.validarDato(frenteA + "", ER_FRENTE_A)) {
            this.frenteA = frenteA;
        }
    }

    public int getFondoR() {
        return fondoR;
    }

    public void setFondoR(int fondoR) {
        if (UtilesValidacion.validarDato(fondoR + "", ER_FONDO_R)) {
            this.fondoR = fondoR;
        }
    }

    public int getFondoV() {
        return fondoV;
    }

    public void setFondoV(int fondoV) {
        if (UtilesValidacion.validarDato(fondoV + "", ER_FONDO_V)) {
            this.fondoV = fondoV;
        }
    }

    public int getFondoA() {
        return fondoA;
    }

    public void setFondoA(int fondoA) {
        if (UtilesValidacion.validarDato(fondoA + "", ER_FONDO_A)) {
            this.fondoA = fondoA;
        }
    }

    // --- FIN SETTERS / GETTERS
    //
    // Items > Modelo
    public void asignarItemsModelo(String[] items) throws Exception {
        // Texto
        setTexto(items[POS_TEXTO]);

        // Familia
        setFamilia(items[POS_FAMILIA]);

        // Estilo
        setNegrita(items[POS_NEGRITA].toLowerCase().equals("true"));
        setCursiva(items[POS_CURSIVA].toLowerCase().equals("true"));

        // Talla
        try {
            setTalla(Integer.parseInt(items[POS_TALLA]));
        } catch (NumberFormatException e) {
            setTalla(DEF_TALLA);
        }

        // Frente
        try {
            setFrenteR(Integer.parseInt(items[POS_FRENTE_R]));
            setFrenteV(Integer.parseInt(items[POS_FRENTE_V]));
            setFrenteA(Integer.parseInt(items[POS_FRENTE_A]));
        } catch (NumberFormatException e) {
            setFrenteR(DEF_FRENTE_R);
            setFrenteV(DEF_FRENTE_V);
            setFrenteA(DEF_FRENTE_A);
        }

        // Fondo
        try {
            setFondoR(Integer.parseInt(items[POS_FONDO_R]));
            setFondoV(Integer.parseInt(items[POS_FONDO_V]));
            setFondoA(Integer.parseInt(items[POS_FONDO_A]));
        } catch (NumberFormatException e) {
            setFondoR(DEF_FONDO_R);
            setFondoV(DEF_FONDO_V);
            setFondoA(DEF_FONDO_A);
        }
    }

    // Modelo > Items
    public String[] asignarModeloItems() {
        // Lista vacía
        String[] items = new String[NUM_ITEMS];

        // Items > Lista
        items[POS_TEXTO] = getTexto();
        items[POS_FAMILIA] = getFamilia();
        items[POS_NEGRITA] = isNegrita() + "";
        items[POS_CURSIVA] = isCursiva() + "";
        items[POS_TALLA] = getTalla() + "";
        items[POS_FRENTE_R] = getFrenteR() + "";
        items[POS_FRENTE_V] = getFrenteV() + "";
        items[POS_FRENTE_A] = getFrenteA() + "";
        items[POS_FONDO_R] = getFondoR() + "";
        items[POS_FONDO_V] = getFondoV() + "";
        items[POS_FONDO_A] = getFondoA() + "";

        // Devuelve Lista
        return items;
    }

    // Duplica Estado Modelo
    public void copiarModelo(Modelo modelo) {
        // Texto
        setTexto(modelo.getTexto());
        
        // Familia
        setFamilia(modelo.getFamilia());
        
        // Estilo
        setNegrita(modelo.isNegrita());
        setCursiva(modelo.isCursiva());
        
        // Tamaño
        setTalla(modelo.getTalla());
        
        // Color Frente
        setFrenteR(modelo.getFrenteR());
        setFrenteV(modelo.getFrenteV());
        setFrenteA(modelo.getFrenteA());

        // Color Fondo
        setFondoR(modelo.getFondoR());
        setFondoV(modelo.getFondoV());
        setFondoA(modelo.getFondoA());
    }
}
