package model;

public class TranslationResult {
    private String translatedText;
    private String detectedLanguage;

    public TranslationResult() {
    }

    public TranslationResult(String translatedText, String detectedLanguage) {
        this.translatedText = translatedText;
        this.detectedLanguage = detectedLanguage;
    }

    public TranslationResult(String translatedText) {
        this.translatedText = translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setDetectedLanguage(String detectedLanguage) {
        this.detectedLanguage = detectedLanguage;
    }

    public String getDetectedLanguage() {
        return detectedLanguage;
    }
}