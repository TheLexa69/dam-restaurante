package edu.badpals.damrestaurante.controllers;

import edu.badpals.damrestaurante.entities.Empresa;
import edu.badpals.damrestaurante.entities.Mesas;
import edu.badpals.damrestaurante.entities.Turno;
import edu.badpals.damrestaurante.entities.UsuarioActual;
import edu.badpals.damrestaurante.models.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class ReservasController {

    private UsuarioActual user;
    private Empresa empresa;
    private List<Empresa> restaurantes = DatabaseConnection.getEmpresas(MainController.em);
    private LocalDate selectedDateLocal;
    private Empresa selectedRestaurantLocal;
    private Turno selectedTurnoLocal;
    private Integer selectedNumComensalesLocal;
    private Mesas selectedMesaLocal;

    @FXML
    private AnchorPane apReservas;

    @FXML
    private Button btnBuscarMesas;

    @FXML
    private Button btnVolver;

    @FXML
    private ChoiceBox<String> choiceRestaurant;

    @FXML
    private DatePicker dpReservas;


    @FXML
    private ListView<String> lvMesas;

    @FXML
    private RadioButton rbManana;

    @FXML
    private RadioButton rbNoche;

    @FXML
    private RadioButton rbTarde;

    @FXML
    private Spinner<Integer> spNumComensales;

    @FXML
    private ToggleGroup turno;

    @FXML
    void getDate(ActionEvent event) {
        LocalDate date = dpReservas.getValue();
        String dateFormattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }



    @FXML
    void getTurno(ActionEvent event) {
        if (rbManana.isSelected()) {
            System.out.println("Selected turno: Mañana");
        } else if (rbTarde.isSelected()) {
            System.out.println("Selected turno: Tarde");
        } else if (rbNoche.isSelected()) {
            System.out.println("Selected turno: Noche");
        }
    }



    @FXML
    public void initialize() {
        dpReservas.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Color de fondo para fechas deshabilitadas
                        }
                    }
                };
            }
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 1);
        valueFactory.setValue(1);
        spNumComensales.setValueFactory(valueFactory);

        choiceRestaurant.getItems().addAll(restaurantes.stream().map(Empresa::getNombreLocal).toList());
        choiceRestaurant.setValue(restaurantes.get(0).getNombreLocal());

        rbManana.setText(Turno.MANANA.getDisplayName());
        rbTarde.setText(Turno.TARDE.getDisplayName());
        rbNoche.setText(Turno.NOCHE.getDisplayName());
    }

    @FXML
    void onBtnClickBuscarMesas(ActionEvent event) {
        // VALIDACIONES DE CAMPOS
        if (choiceRestaurant.getValue() == null || choiceRestaurant.getValue().isEmpty()) {
            showAlert("Campo Obligatorio", "Por favor, selecciona un restaurante.");
            return;
        }

        LocalDate date = dpReservas.getValue();
        if (date == null) {
            showAlert("Campo Obligatorio", "Por favor, selecciona una fecha para la reserva.");
            return;
        }

        if (turno.getSelectedToggle() == null) {
            showAlert("Campo Obligatorio", "Por favor, selecciona un turno (Mañana, Tarde o Noche).");
            return;
        }

        Integer numComensales = spNumComensales.getValue();
        if (numComensales == null || numComensales < 1) {
            showAlert("Campo Obligatorio", "Por favor, selecciona un número válido de comensales.");
            return;
        }

        // FIN VALIDACIONES DE CAMPOS
        String dateFormattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String selectedTurno = ((RadioButton) turno.getSelectedToggle()).getText();

        System.out.println("Selected restaurant: " + choiceRestaurant.getValue());
        System.out.println("Selected date: " + dateFormattedDate);
        System.out.println("Selected turno: " + selectedTurno);
        System.out.println("Selected num comensales: " + numComensales);

        Turno turno = Turno.fromDisplayName(selectedTurno);
        Date sqlDate = Date.valueOf(date);

        Empresa empresa = DatabaseConnection.getEmpresaPorNombre(MainController.em, choiceRestaurant.getValue());
        List<Mesas> mesasDisponibles = DatabaseConnection.getMesasPorFechaTurno(MainController.em, turno, sqlDate, numComensales, empresa);

        if (mesasDisponibles.isEmpty()) {
            showAlert("Mesas disponibles", "No hay mesas disponibles para la selección.");
        } else {
            System.out.println("Mesas disponibles: " + Arrays.toString(mesasDisponibles.toArray()));
            lvMesas.getItems().clear();
            lvMesas.getItems().addAll(mesasDisponibles.stream().map(Mesas::getEnumMesa).toList());

            lvMesas.setOnMouseClicked(mouseEvent -> {
                String selectedMesa = lvMesas.getSelectionModel().getSelectedItem();
                if (selectedMesa != null) {
                    // Buscar información de la mesa seleccionada
                    Mesas mesaSeleccionada = mesasDisponibles.stream()
                            .filter(mesa -> mesa.getEnumMesa().equals(selectedMesa))
                            .findFirst()
                            .orElse(null);

                    if (mesaSeleccionada != null) {
                        System.out.println("Información de la mesa seleccionada:");
                        System.out.println("ID: " + mesaSeleccionada.getIdMesa());
                        System.out.println("Cupo: " + mesaSeleccionada.getCupo());
                        System.out.println("Empresa: " + mesaSeleccionada.getIdEmpresa());

                        //SETAMOS LOS VALORES DE LA RESERVA
                        selectedDateLocal = date;
                        selectedRestaurantLocal = empresa;
                        selectedTurnoLocal = turno;
                        selectedNumComensalesLocal = numComensales;
                        selectedMesaLocal = mesaSeleccionada;
                    } else {
                        System.out.println("Mesa seleccionada no encontrada.");
                    }
                }
            });
        }

    }

    @FXML
    void onBtnClickReservar(ActionEvent event) {
        if (selectedDateLocal == null || selectedRestaurantLocal == null || selectedTurnoLocal == null || selectedNumComensalesLocal == null || selectedMesaLocal == null) {
            showAlert("Error", "Por favor, selecciona una mesa para la reserva.");
            return;
        }

        System.out.println("Reservando mesa...");
        System.out.println("Fecha: " + selectedDateLocal);
        System.out.println("Restaurante: " + selectedRestaurantLocal);
        System.out.println("Turno: " + selectedTurnoLocal);
        System.out.println("Comensales: " + selectedNumComensalesLocal);
        System.out.println("Mesa: " + selectedMesaLocal.getEnumMesa());

        Date sqlDate = Date.valueOf(selectedDateLocal);


        DatabaseConnection.reservarMesa(MainController.em, selectedTurnoLocal, sqlDate, selectedRestaurantLocal, user, selectedMesaLocal );
        showAlert("Reserva realizada", "Reserva realizada con éxito.");
    }

    @FXML
    void onBtnClickVolver(ActionEvent event) {
        MainController.redirectToIndexChangeUser(btnVolver, user);
    }










    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public UsuarioActual getUser() {
        return user;
    }

    public void setUser(UsuarioActual user) {
        this.user = user;
    }
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }


}
