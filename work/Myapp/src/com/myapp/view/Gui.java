package com.myapp.view;

import com.myapp.Main;
import com.myapp.production.CreateQuestion;
import com.myapp.util.FileUtil;
import com.sun.xml.internal.ws.util.StringUtils;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.util.List;

public class Gui extends Application {
    /**
        布局类
     */
    private Group group;

    /**
     *顶级容器
     */
    private Stage primaryStage;
    /**
     * 题目文件
     */
    private String questionFile;
    /**
     * 答案文件
     */
    private String answerfile;


    public static void main(String[] args) {
        Application.launch(Gui.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        //窗口标题
        primaryStage.setTitle("四则运算生成器");
        //窗口图标
        primaryStage.getIcons().add(new Image("images/icon.jpg"));
        //固定窗口大小，不可调(默认true可调)
        primaryStage.setResizable(false);
        primaryStage.setWidth(500);
        primaryStage.setHeight(470);
        this.group = new Group();

        //滚动条
//        ScrollBar sc = new ScrollBar();
//        sc.setMin(0);
//        sc.setMax(500);
//        sc.setValue(50);
//        sc.valueProperty().addListener((ObservableValue<? extends Number> ov,
//                                        Number old_val, Number new_val) -> {
//            System.out.println(-new_val.doubleValue());
//        });
//        group.getChildren().add(sc);

        createQuestion();
        checkFunction();

        createAnswerFile();
        //设置scene后窗口放大缩小不会再有黑色
        primaryStage.setScene(new Scene(group));
        primaryStage.show();
    }

    private Button creatButton(double x, double y, String name) {
        Button button = new Button(name);
        button.setPrefHeight(30);
        button.setPrefWidth(90);
        button.setStyle(
                "-fx-text-fill:#B3EE3A;"+
                        "-fx-Background-color:#1E90FF;"+
                        "-fx-Border-color:#CD0000;"+
                        "-fx-Border-StrokeStyle:SOLID;"
        );
        button.setLayoutX(x);
        button.setLayoutY(y);
        //鼠标在按钮上以手显示
        button.setCursor(Cursor.HAND);
        return button;
    }
    /**
     * 题目生成功能
     */
    private void createQuestion() {
        //标签
        Label label = new Label("请输入生成的题目数量及数据范围:");
        label.setFont(Font.font(20));
        label.setTextFill(Paint.valueOf("black"));
        TextField num = new TextField();
        num.setPromptText("请输入题目数量");
        num.setLayoutY(30);
        TextField range = new TextField();
        range.setPromptText("请输入数据范围");
        range.setLayoutY(60);
        //Button属于node
        Button button = creatButton(250, 60, "一键生成");
        //绑定鼠标点击事件
        button.setOnAction(event -> {
            if (!num.getText().equals("") && !range.getText().equals("")) {
                CreateQuestion createQuestionR = new CreateQuestion(Integer.parseInt(num.getText()), Integer.parseInt(range.getText()));
                FileUtil.writeQuestion(createQuestionR.CreateQuestions(), Main.QUESTION_FILE_NAME, Main.ANSWER_FILE_NAME);
                num.setText("");
                range.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("信息");
                alert.headerTextProperty().set("请分别输入生成的题目数量及数据范围！");
                alert.showAndWait();
            }
        });

        //横线
        Line line = new Line(0, 100,   500,   100);
        //添加布局
        group.getChildren().addAll(label,num,range,button, line);
    }

    private Button createFileSelect(String buttonName, int x, int y) {
        Button fileButton = new Button(buttonName);
        fileButton.setLayoutY(y);
        fileButton.setLayoutX(x);
        fileButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                //打开当前路径
                fileChooser.setInitialDirectory(new File("."));
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    if ("选择题目".equals(buttonName)) {
                        questionFile = file.getAbsolutePath();
                    }else if("选择答案".equals(buttonName)){
                        answerfile = file.getAbsolutePath();
                    }
                }
            }
        });
        return fileButton;
    }
    /**
     * 校验功能
     */

    private void checkFunction(){

        Label checkLabel = new Label("请选择题目文件和答案文件进行校验:");
        checkLabel.setFont(Font.font(20));
        checkLabel.setTextFill(Paint.valueOf("black"));
        checkLabel.setLayoutY(120);

//        Label correct = new Label("Correct: ");
//        correct.setFont(Font.font(20));
//        correct.setTextFill(Paint.valueOf("green"));
//        correct.setLayoutY(200);
//
//        Label wrong = new Label("Wrong: ");
//        wrong.setFont(Font.font(20));
//        wrong.setTextFill(Paint.valueOf("red"));
//        wrong.setLayoutY(250);

        TextArea textArea = new TextArea("Correct:");
        textArea.setFont(Font.font(20));
        textArea.setPrefWidth(500);
        textArea.setPrefHeight(20);
        textArea.setMaxWidth(500);
        textArea.setLayoutX(0);
        textArea.setLayoutY(200);

        TextArea textArea2 = new TextArea("Wrong: ");
        textArea2.setFont(Font.font(20));
        textArea2.setStyle("-fx-text-fill:red;");
        textArea2.setPrefWidth(500);
        textArea2.setPrefHeight(20);
        textArea2.setMaxWidth(500);
        textArea2.setLayoutX(0);
        textArea2.setLayoutY(250);

        Button selectQustionFile = createFileSelect("选择题目", 0, 150);
        Button selectAnswerFile = createFileSelect("选择答案", 80, 150);
        Button button = creatButton(250,  150, "一键校验");
        //绑定鼠标点击事件
        button.setOnAction(event -> {
            if (questionFile != null && answerfile != null) {

                List<Integer>[] result = Main.exercisesCheck(questionFile, answerfile);
                if (result != null) {
//                    correct.setText("Correct: " + result[0].size() + result[0]);
//                    wrong.setText("Wrong: " + result[1].size() + result[1]);

                    textArea.setText("Correct: " + result[0].size() + result[0]);
                    textArea2.setText("Wrong: " + result[1].size() + result[1]);
                }
                questionFile = null;
                answerfile = null;
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("信息");
                if (questionFile == null) {
                    alert.headerTextProperty().set("请选择题目文件！");
                } else {
                    alert.headerTextProperty().set("请选择答案文件！");
                }
                alert.showAndWait();
            }

        });
        Button button1 = creatButton(350,150, "一键清空");
        button1.setOnAction(event -> {
            questionFile = null;
            answerfile = null;
//            correct.setText("Correct: ");
//            wrong.setText("Wrong: ");
            textArea.setText("Correct: ");
            textArea2.setText("Wrong: ");
        });
        //横线
        Line line = new Line(0, 315,   500,   315);
        this.group.getChildren().addAll(checkLabel, selectQustionFile, selectAnswerFile, button, button1, line, textArea, textArea2);
    }

    private void createAnswerFile() {
        Label creatLabel = new Label("计算题目文件，生成对应答案文件");
        creatLabel.setFont(Font.font(20));
        creatLabel.setTextFill(Paint.valueOf("black"));
        creatLabel.setLayoutY(320);
        Button selectFile = createFileSelect("选择题目",0, 360);
        Button button = creatButton(250,  360,"一键生成");
        button.setOnAction(event -> {
            if (questionFile != null) {
                Main.creatAnswerFile(questionFile);
                questionFile = null;
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.titleProperty().set("信息");
                alert.headerTextProperty().set("请选择题目文件！");
                alert.showAndWait();
            }
        });
        this.group.getChildren().addAll(creatLabel, selectFile, button);
    }

}
