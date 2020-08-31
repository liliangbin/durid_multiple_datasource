package com.example.oracle.rdbs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;

public class JsonSchemaChange {
    private static final Logger LOGGER = Logger.getLogger(JsonSchemaGenerator.class.getName());
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String outputAsString(String title, String description,
                                        String json) throws IOException {
        return cleanup(outputAsString(title, description, json, null, "root"));
    }

    private static String outputAsString(String title, String description,
                                         String json, JsonNodeType type, String parentPath) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(json);
        StringBuilder output = new StringBuilder();
        output.append("{");

  /*      for (Iterator<JsonNode> lists = jsonNode.elements(); lists.hasNext();
        ) {

            JsonNode jsonNodeChild = lists.next();
        }*/

        for (Iterator<String> iterator = jsonNode.fieldNames(); iterator.hasNext(); ) { //获取当前层级有那些字段
            String fieldName = iterator.next();

            LOGGER.info("processing " + fieldName + "...");

            JsonNodeType nodeType = jsonNode.get(fieldName).getNodeType();

            output.append(convertNodeToStringSchemaNode(jsonNode, nodeType, fieldName, parentPath));
            if (iterator.hasNext()) {
                output.append(",");
            }
        }


        output.append("}");

        LOGGER.info("generated schema = " + output.toString());
        return output.toString();
    }

    //可以在type的基础上添加字段===>

    private static String convertNodeToStringSchemaNode(
            JsonNode jsonNode, JsonNodeType nodeType, String key, String parentPath) throws IOException {
        StringBuilder result = new StringBuilder("\"" + key + "\":");
        String currentPath = parentPath + "," + key;
        LOGGER.info(key + " node type " + nodeType + " with value " + jsonNode.get(key));

        JsonNode node = null;
        switch (nodeType) {
            case ARRAY:
                node = jsonNode.get(key).get(0);
                LOGGER.info(key + " is an array with value of " + node.toString());
                result.append(outputAsString(null, null, node.toString(), JsonNodeType.ARRAY, currentPath));
                break;
            case BOOLEAN:
                result.append("{\"type\":\"boolean\",\"name\":\" " + currentPath + "\" }");

                break;
            case NUMBER:
                result.append("{\"type\":\"number\" ,\"name\":\" " + currentPath + "\" }");
                break;
            case OBJECT:
                node = jsonNode.get(key);
                result.append(outputAsString(null, null, node.toString(), JsonNodeType.OBJECT, currentPath));

                break;
            case STRING:
                result.append("{\"type\":\"string\",\"name\":\"" + currentPath + "\" }");
                break;
            default:
        }

        return result.toString();
    }

    private static String cleanup(String dirty) {
        System.out.println(dirty);
        System.out.println("miaomiaomiao ");
        JSONObject rawSchema = new JSONObject(new JSONTokener(dirty));
        Schema schema = SchemaLoader.load(rawSchema);
        return schema.toString();
    }

    public static void main(String[] args) throws IOException {
        String json = "{\"createdAt\":\"2017-07-19T16:31:26.843Z\"," +
                "\"sectors\":[{\"times\":[{\"intensity\":30,\"start\":{\"hour\":8,\"minute\":30}," +
                "\"end\":{\"hour\":17,\"minute\":0}},{\"intensity\":10,\"start\":{\"hour\":17,\"minute\":5}," +
                "\"end\":{\"hour\":23,\"minute\":55}}],\"id\":\"dbea21eb-57b5-44c9-a953-f61816fd5876\"}]," +
                "\"dayOfWeek\":\"0,6\",\"createdBy\":\"Admin\",\"name\":\"test weekend preset\"," +
                "\"client\":\"TestClient\",\"id\":\"83d6640a-6d80-487c-b92c-e4239e1ec6d5\"}";
        String json2 = "[{\"run\":[{\"hello \":23,\"hh\":23},{\"hello\":23,\"hh\":22},{\"hello\":32,\"hh\":67}],\"test\":[{\"dd\":22,\"fsdf\":23},{\"dd\":34,\"fsdf\":455},{\"dd\":12,\"fsdf\":44}]},{\"run\":[{\"hello \":23,\"hh\":23},{\"hello\":23,\"hh\":22},{\"hello\":32,\"hh\":67}],\"test\":[{\"dd\":22,\"fsdf\":23},{\"dd\":34,\"fsdf\":455},{\"dd\":12,\"fsdf\":44}]}]";

        String result = JsonSchemaChange.outputAsString("Schedule", "this is a test", json);
        System.out.println(result);
    }
}
