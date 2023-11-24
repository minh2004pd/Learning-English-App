package model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;

public class Translate {
    public static String detectLang(String text) throws APIError {
        System.out.println(text);
        DetectLanguage.apiKey = "ec95e545d280d386c3eb29abc76b13ac";
        List<Result> results = DetectLanguage.detect(text);
        Result result = results.get(0);
        return result.language;
    }
    public static TranslationResult googleTranslate(String langFrom, String langTo, String text) throws IOException, APIError {
        String urlScript = "https://script.google.com/macros/s/AKfycbw1qSfs1Hvfnoi3FzGuoDWijwQW69eGcMM_iGDF7p5vu1oN_CaFqIDFmCGzBuuGCk_N/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlScript);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String translation = response.toString();
        return new TranslationResult(translation, langFrom);
    }

    public static void main(String[] args) {
        try {
            String res = detectLang("xin chào");
            System.out.println("Translated Text: " + "xin chào");
            System.out.println("Detected Language: " + res);
        } catch (APIError e) {
            e.printStackTrace();
        }
    }
}