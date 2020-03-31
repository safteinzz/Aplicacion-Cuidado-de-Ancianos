/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pinfui.entidades;

/**
 *
 * @author SaFteiNZz
 */
public class Etiqueta {
    
        private int id_etiqueta;
        private String etiqueta;

        public Etiqueta(int id_etiqueta, String etiqueta) {
            this.id_etiqueta = id_etiqueta;
            this.etiqueta = etiqueta;
        }   


        
        
        
        public int getId_etiqueta() {
            return id_etiqueta;
        }

        public void setId_etiqueta(int id_etiqueta) {
            this.id_etiqueta = id_etiqueta;
        }

        public String getEtiqueta() {
            return etiqueta;
        }

        public void setEtiqueta(String etiqueta) {
            this.etiqueta = etiqueta;
        }    
}
