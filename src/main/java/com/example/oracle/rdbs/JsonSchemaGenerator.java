/*******************************************************************************
 * Copyright (c) 2017-2027 nd Corporation and others.
 * All rights reserved. 
 *
 * http://www.nd.com.cn/
 *******************************************************************************/
package com.example.oracle.rdbs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;


import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;


/**
 * @Package: com.nd
 * @Description: 从json字符转数据中获取json的结构
 * @author: 198530 kidchild@163.com
 * @date: 2020/8/13  15:46
 * @version: V1.0
 * <p>
 * Modification History:
 * Date                       Author         Version          Description(summary)
 * ------------------------------------------------------------------------------------
 * 2020/8/13  15:46       198530         1.0             Create class.
 * <p>
 * Why & What is modified(detail): <give me a reason>
 */
public final class JsonSchemaGenerator {
    private static final Logger LOGGER = Logger.getLogger(JsonSchemaGenerator.class.getName());
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String outputAsString(String title, String description,
                                        String json) throws IOException {
        return cleanup(outputAsString(title, description, json, null));
    }

    private static String outputAsString(String title, String description,
                                         String json, JsonNodeType type) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(json);
        StringBuilder output = new StringBuilder();
        output.append("{");

        if (type == null) {
            output.append(
                    "\"title\": \"" +
                            title + "\", \"description\": \"" +
                            description + "\", \"type\": \"object\", \"properties\": {");
        }

        for (Iterator<String> iterator = jsonNode.fieldNames(); iterator.hasNext();) {
            String fieldName = iterator.next();

            LOGGER.info("processing " + fieldName + "...");

            JsonNodeType nodeType = jsonNode.get(fieldName).getNodeType();

            output.append(convertNodeToStringSchemaNode(jsonNode, nodeType, fieldName));
        }

        if (type == null) {
            output.append("}");
        }

        output.append("}");

        LOGGER.info("generated schema = " + output.toString());
        return output.toString();
    }

    //可以在type的基础上添加字段===>

    private static String convertNodeToStringSchemaNode(
            JsonNode jsonNode, JsonNodeType nodeType, String key) throws IOException {
        StringBuilder result = new StringBuilder("\"" + key + "\": { \"type\": \"");

        LOGGER.info(key + " node type " + nodeType + " with value " + jsonNode.get(key));
        JsonNode node = null;
        switch (nodeType) {
            case ARRAY :
                node = jsonNode.get(key).get(0);
                LOGGER.info(key + " is an array with value of " + node.toString());
                result.append("array\", \"items\": { \"properties\":");
                result.append(outputAsString(null, null, node.toString(), JsonNodeType.ARRAY));
                result.append("}},");
                break;
            case BOOLEAN:
                result.append("boolean\" },");
                break;
            case NUMBER:
                result.append("number\" },");
                break;
            case OBJECT:
                node = jsonNode.get(key);
                result.append("object\", \"properties\": ");
                result.append(outputAsString(null, null, node.toString(), JsonNodeType.OBJECT));
                result.append("},");
                break;
            case STRING:
                result.append("string\" },");
                break;
        }

        return result.toString();
    }

    private static String cleanup(String dirty) {
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
        String result = JsonSchemaGenerator.outputAsString("Schedule", "this is a test", json);
        System.out.println(result);
    }
}
