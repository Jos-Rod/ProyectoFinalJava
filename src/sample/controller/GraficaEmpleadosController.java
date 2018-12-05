package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;
import sample.api.TareaAPI;
import sample.model.Tarea;

import java.util.List;

public class GraficaEmpleadosController {

    public Main mainapp;

    @FXML
    private Button btnVolver;

    @FXML
    private BarChart<String, Integer> BarChartGrafica;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private void handleVolver() {

        mainapp.showEmpleados();
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();

    }

    public void ponerGrafica() {

        //poner credito
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

        List<Tarea> listaTareas = tapi.getAll();

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

        BarChartGrafica.getData().add(series);

    }

}
