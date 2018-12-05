package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Main;
import sample.api.TareaAPI;
import sample.model.Tarea;
import sample.model.Usuario;

import java.util.List;


public class GraficaController {

    public Main mainapp;

    public Usuario usuarioGrafica;

    @FXML
    private BarChart<String, Integer> BarEmpleados;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblTitulo;

    @FXML
    private void handleBack(){

        mainapp.showEmpleadoSeleccionado(usuarioGrafica);
        Stage stage = (Stage) xAxis.getScene().getWindow();
        stage.close();

    }

    public void ponerGrafica() {
        lblTitulo.setText("Gráfica de " + usuarioGrafica.getNombre());

        //poner Grafica
        //lista de items categories
        ObservableList<String> olCategorias = FXCollections.observableArrayList();
        olCategorias.add("Pendiente");
        olCategorias.add("En Proceso");
        olCategorias.add("Terminado");
        int cuentaPendiente = 0;
        int cuentaEnProceso = 0;
        int cuentaTerminado = 0;

        xAxis.setCategories(olCategorias);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        TareaAPI tapi = new TareaAPI();

        List<Tarea> listaTareas = tapi.getAllFromUser(usuarioGrafica.getIdUser());

        for (Tarea tarea : listaTareas) {
            if (tarea.getEstado().equals("Pendiente")) {
                cuentaPendiente++;
            } else if (tarea.getEstado().equals("En Proceso")) {
                cuentaEnProceso++;
            } else if (tarea.getEstado().equals("Terminado")) {
                cuentaTerminado++;
            }
        }

        series.getData().add(new XYChart.Data<>("Pendiente", cuentaPendiente));
        series.getData().add(new XYChart.Data<>("En Proceso", cuentaEnProceso));
        series.getData().add(new XYChart.Data<>("Terminado", cuentaTerminado));

        BarEmpleados.getData().add(series);

//        xAxis.setCategories(olmonths);
//        XYChart.Series<String, Integer> series = new XYChart.Series<>();
//        for (int i = 0; i < months.length; i++) {
//            int cuenta = 0;
//            for (Employee e:
//                    listaE) {
//                if ((e.getDob().getMonth()) == i) {
//                    cuenta++;
//                }
//            }
//            series.getData().add(new XYChart.Data<>(months[i], cuenta));
//        }
//
//        barChart1.getData().add(series);


    }
}
