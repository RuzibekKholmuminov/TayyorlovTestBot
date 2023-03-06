package org.example;

public class Word {
    public Word() {
    }

    private Integer id;
    private String origin;
    private String translator;
    private String translator_id;


    @Override
    public String toString() {
        return
                "id=" + id +
                        ", origin='" + origin + '\'' +
                        ", translator='" + translator + '\'' +
                        ", translator_id='" + translator_id + '\'';
    }

    public Word(String origin) {

    }

    public String getTranslator() {
        return translator;
    }

    public String getTranslator_id() {
        return translator_id;
    }

    public void setTranslator_id(String translator_id) {
        this.translator_id = translator_id;
    }

    public Word(Integer id, String origin, String translator, String translator_id) {
        this.id = id;
        this.origin = origin;
        this.translator = translator;
        this.translator_id = translator_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }



    public void setTranslator(String translator) {
        this.translator = translator;
    }
}
