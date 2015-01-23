   
/**     
 * @version: 
 * @date: 2014年4月24日 上午2:52:28       
 * @author: rock 
 * @modified by: rock
 * @modified date: 2014年4月24日 上午2:52:28
 * @modified content: 修改内容           
 */
 
package com.hdg.rjra.base.utils;

  

/**   
 * @title: StringUtils.java   
 * @description: 类功能描述      
 * @date: 2014年4月24日 上午2:52:28
 * @author: rock
 * @modify by: rock
 * @modify date: 2014年4月24日 上午2:52:28
 * @modify content: 修改内容  
 *   
 */
public class StringUtils {

    /**
     * 判断字符串为空
     * 
     * @param str
     *            目标串
     * @return boolean
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str.trim())) {
            return true;
        }
        return false;
    }
    
    /**
     * 判断字符串不为空
     * 
     * @param str
     *            目标串
     * @return boolean
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String longArrayToString(Long[] longs) {
        if (longs == null) {
            return "";
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < longs.length; i++) {
                sb.append(longs[i]);
                if (i < longs.length - 1) {
                    sb.append(",");
                }
            }
            return sb.toString();
        }
    }

    public static Long[] stringToLongArray(String str){
        if (StringUtils.isEmpty(str)) {
            return new Long[]{};
        }else{
          String[] strings =  str.split(",");
            Long[] longs = new Long[strings.length];
            for (int i = 0; i < strings.length; i++) {
                longs[i] = Long.valueOf(strings[i]);
            }
            return longs;
        }
    }
}
