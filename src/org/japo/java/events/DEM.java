package org.japo.java.events;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.japo.java.controllers.EventsController;

public class DEM implements DocumentListener {

    // Referencia al EventsController
    private final EventsController eventsControl;

    // Constructor
    public DEM(EventsController eventsControl) {
        this.eventsControl = eventsControl;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        eventsControl.procesarEventosDocumento(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        eventsControl.procesarEventosDocumento(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        eventsControl.procesarEventosDocumento(e);
    }
}
