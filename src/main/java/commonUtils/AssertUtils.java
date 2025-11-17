package commonUtils;

import com.aventstack.extentreports.markuputils.MarkupHelper;
import listener.TestListener;
import reports.ExtentManager;

import java.util.*;

public class AssertUtils {

    public static void assertNVerify(Object sentData,Object recvData) {
        List<AssertionKeys> assertionKeysList = new ArrayList<>();
        boolean isMatched = true;
        AssertionKeys assertionKeys = new AssertionKeys("JsonPath", "Expected_Value", "Actual_Value", "Result");
        assertionKeysList.add(assertionKeys);
        if (sentData instanceof Map && recvData instanceof Map) {
            Map<String, Object> sentDataInMapFormat = (Map) sentData;
            Map<String, Object> recvDataInMapFormat = (Map) recvData;

            Set<String> sentDataKeys = sentDataInMapFormat.keySet();
            for (String eachKey : sentDataKeys) {
                Optional<Object> optionalValue = Optional.ofNullable(sentDataInMapFormat.get(eachKey));
                if (optionalValue.isPresent()) {
                    Object sentValue = sentDataInMapFormat.get(eachKey);
                    Object recvValue = recvDataInMapFormat.get(eachKey);
                    if (sentValue.equals(recvValue)) {
                        assertionKeysList.add(new AssertionKeys(eachKey, recvValue, sentValue, "Matched"));
                    } else {
                        isMatched = false;
                        assertionKeysList.add(new AssertionKeys(eachKey, recvValue, sentValue, "Not Matched"));
                        break;
                    }
                }
            }

            if (isMatched) {
                try {
                    String[][]assertedData=assertionKeysList.stream().map(AssertionKeys->new String[]{AssertionKeys.getJsonPath(),
                            String.valueOf(AssertionKeys.getExpectedValue()),
                            String.valueOf(AssertionKeys.getActualValue()),AssertionKeys.getResult()}).toArray( String[][]::new);

                    TestListener.extentTestThreadLocal.get().info(MarkupHelper.createTable(assertedData, "table-sm"));
                  //  return isMatched;

                    //     TestListener.extentTestThreadLocal.get().info(MarkupHelper.createOrderedList(assertionKeysList));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {
                String[][]assertedData=assertionKeysList.stream().map(AssertionKeys->new String[]{AssertionKeys.getJsonPath(),
                        String.valueOf(AssertionKeys.getExpectedValue()),
                        String.valueOf(AssertionKeys.getActualValue()),AssertionKeys.getResult()}).toArray( String[][]::new);
            TestListener.extentTestThreadLocal.get().info(MarkupHelper.createTable(assertedData,"table-sm"));
            ExtentManager.logException("Assertion failed");


            }


        }

        //*************************


    }

    public static boolean validateIntegerArray(List<Integer> list){
        boolean isValid=true;
    if(list ==null || list.isEmpty()){
        isValid=false;
        //return isValid;
    }
    for(Integer i:list ){
          if(i == null || i == 0){
           isValid=false;
        }

    }
    return isValid;
    }



}
