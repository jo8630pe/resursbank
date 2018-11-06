package sodukosolver;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TextFieldLimited extends TextField {  
    private int maxlength;
    private StringProperty restrict = new SimpleStringProperty(this, "restrict");
 
    
    public TextFieldLimited() {
        this.maxlength = 1;
        
        textProperty().addListener(new ChangeListener<String>() {
        	 
            private boolean ignore;
 
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s1) {
                if (ignore || s1 == null)
                    return;
 
                if (restrict.get() != null && !restrict.get().equals("") && !s1.matches(restrict.get() + "*")) {
                    ignore = true;
                    setText(s);
                    ignore = false;
                }
            }
        });
        
        
    }
    
    @Override
    public void replaceText(int start, int end, String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceText(start, end, text);
        } else if (getText().length() < maxlength) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        // Delete or backspace user input.
        if (text.equals("")) {
            super.replaceSelection(text);
        } else if (getText().length() < maxlength) {
            // Add characters, but don't exceed maxlength.
            if (text.length() > maxlength - getText().length()) {
                text = text.substring(0, maxlength- getText().length());
            }
            super.replaceSelection(text);
        }
    }

    /**
     * The restrict property.
     *
     * @return The restrict property.
     */
    public StringProperty restrictProperty() {
        return restrict;
    }

    /**
     * Sets a regular expression character class which restricts the user input.
 
     * E.g. [0-9] only allows numeric values.
     *
     * @param restrict The regular expression.
     */
    public void setRestrict(String restrict) {
        this.restrict.set(restrict);
    }
}

