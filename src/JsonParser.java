import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    private static final Pattern REGEX_ITENS = Pattern.compile(".*\\[(.+)\\].*");
    private static final Pattern REGEX_ATRIBUTO_JSON = Pattern.compile("\"(.+?)\":\"(.*?)\"");

    public List<Map<String, String>> parse(String json){
        Matcher matcher = REGEX_ITENS.matcher(json);
        if (!matcher.find()){
            throw new IllegalArgumentException("Não encontrou items.");
        }

        String[] items = matcher.group(1).split("\\},\\{");

        List<Map<String, String>> dados = new ArrayList<>();

        for(String item: items){
            Map<String, String> atriutosItem = new HashMap<>();

            Matcher matcherAtributoJson = REGEX_ATRIBUTO_JSON.matcher(item);

            while(matcherAtributoJson.find()){
                String atributo = matcherAtributoJson.group(1);
                String valor = matcherAtributoJson.group(2);
                atriutosItem.put(atributo,valor);
            }

            dados.add(atriutosItem);

        }
        return dados;
    }
}
