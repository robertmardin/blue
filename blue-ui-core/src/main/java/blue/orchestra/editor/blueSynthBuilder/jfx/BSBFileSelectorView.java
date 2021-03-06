/*
 * blue - object composition environment for csound
 * Copyright (C) 2016
 * Steven Yi <stevenyi@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package blue.orchestra.editor.blueSynthBuilder.jfx;

import blue.BlueSystem;
import blue.orchestra.blueSynthBuilder.BSBFileSelector;
import blue.ui.nbutilities.BlueNbUtilities;
import blue.ui.utilities.FileChooserManager;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javax.swing.SwingUtilities;
import org.openide.util.Exceptions;
import org.openide.windows.WindowManager;

/**
 *
 * @author stevenyi
 */
public class BSBFileSelectorView extends BorderPane implements ResizeableView {

    private static final int FILE_BUTTON_WIDTH = 30;
    private static int OBJECT_HEIGHT = 30;
    private static String FILE_SELECTOR_ID = "BSBFileSelector";

    private final BSBFileSelector fileSelector;

    TextField fileNameField;

    public BSBFileSelectorView(BSBFileSelector fileSelector) {
        setUserData(fileSelector);
        this.fileSelector = fileSelector;

        fileNameField = new TextField();
        fileNameField.setPrefHeight(OBJECT_HEIGHT);
        fileNameField.setEditable(false);
        fileNameField.setPromptText("Select a File");

        MenuItem clearMenuItem = new MenuItem("Clear");
        clearMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileSelector.fileNameProperty().set("");
            }
        });
        ContextMenu menu = new ContextMenu(clearMenuItem);
        fileNameField.setContextMenu(menu);

        Button btn = new Button("...");
        btn.setPrefWidth(FILE_BUTTON_WIDTH);
        btn.setPrefHeight(OBJECT_HEIGHT);

        setCenter(fileNameField);
        setRight(btn);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                String fileName = fileSelector.getFileName();

                if (fileName != null && fileName.trim().length() > 0) {
                    File file = BlueSystem.findFile(fileName);

                    if (file != null) {
                        FileChooserManager.getDefault().setSelectedFile(
                                FILE_SELECTOR_ID,
                                file);
                    }
                }
                
                List<File> rValue = FileChooserManager.getDefault().showOpenDialog(
                        FILE_SELECTOR_ID, BlueNbUtilities.getMainWindow());

                if (!rValue.isEmpty()) {
                    File f = rValue.get(0);

                    try {
                        String absFilePath = f.getCanonicalPath();
                        String relPath = BlueSystem.getRelativePath(absFilePath);

                        System.out.println("Rel Path: " + relPath);
                        fileSelector.setFileName(relPath);
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                }
            }
        });

        setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasFiles() || dragboard.hasString()
                        || dragboard.hasUrl()) {
                    event.acceptTransferModes(TransferMode.LINK);
                }
                event.consume();
            }
        });

        setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();

                if (dragboard.hasFiles()) {

                    List<File> files = dragboard.getFiles();
                    if (files.size() != 1) {

                        event.setDropCompleted(false);
                        event.consume();
                        return;
                    }

                    File f = files.get(0);

                    if (f.exists() && f.isFile()) {
                        try {
                            String absFilePath = f.getCanonicalPath();
                            String relPath = BlueSystem.getRelativePath(
                                    absFilePath);

                            fileSelector.setFileName(relPath);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        event.setDropCompleted(true);
                        event.consume();
                        return;
                    }
                    event.setDropCompleted(false);
                } else if (dragboard.hasString()) {
                    String s = dragboard.getString().trim();
                    if (!s.startsWith("file://")) {
                        event.setDropCompleted(false);
                        event.consume();
                        return;
                    }
                    s = s.substring(7).trim();
                    s = URLDecoder.decode(s);
                    s = s.replaceAll(" ", "\\ ");

                    File f = new File(s);

                    if (f.exists() && f.isFile()) {

                        try {
                            String absFilePath = f.getCanonicalPath();
                            String relPath = BlueSystem.getRelativePath(
                                    absFilePath);

                            fileSelector.setFileName(relPath);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        event.setDropCompleted(true);
                        event.consume();
                        return;
                    }
                } else if (dragboard.hasUrl()) {
                    String val = dragboard.getUrl();
                    System.out.println("URL: " + val);
                    File f = new File(val);

                    if (f.exists() && f.isFile()) {

                        try {
                            String absFilePath = f.getCanonicalPath();
                            String relPath = BlueSystem.getRelativePath(
                                    absFilePath);

                            fileSelector.setFileName(relPath);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        event.setDropCompleted(true);
                        event.consume();
                        return;
                    }
                }

                event.setDropCompleted(false);
            }

        });

        sceneProperty().addListener((obs, old, newVal) -> {
            if (newVal == null) {
                fileNameField.textProperty().unbind();
                fileNameField.prefWidthProperty().unbind();
            } else {
                fileNameField.textProperty().bind(fileSelector.fileNameProperty());
                fileNameField.prefWidthProperty().bind(fileSelector.textFieldWidthProperty());
            }
        });
    }

    public boolean canResizeWidgetWidth() {
        return true;
    }

    public boolean canResizeWidgetHeight() {
        return false;
    }

    public int getWidgetMinimumWidth() {
        return 10 + FILE_BUTTON_WIDTH;
    }

    public int getWidgetMinimumHeight() {
        return -1;
    }

    public int getWidgetWidth() {
        return fileSelector.getTextFieldWidth();
    }

    public void setWidgetWidth(int width) {
        fileSelector.setTextFieldWidth(Math.max(10, width));
    }

    public int getWidgetHeight() {
        return -1;
    }

    public void setWidgetHeight(int height) {
    }

    public void setWidgetX(int x) {
        fileSelector.setX(x);
    }

    public int getWidgetX() {
        return fileSelector.getX();
    }

    public void setWidgetY(int y) {
    }

    public int getWidgetY() {
        return -1;
    }
}
