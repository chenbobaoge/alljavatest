package org.bobochen.dal.tool;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class OddiffResultSetMapper {
    protected static Log logger = LogFactory.getLog(OddiffResultSetMapper.class);

    public <T> List<T> mapRersultSetToObject(ResultSet rs, Class outputClass, boolean isOnlyFirst) {
        boolean isBaseType = false;
        String classname = outputClass.getName().toLowerCase();
        if (classname.endsWith("string") || classname.endsWith("int") || classname.endsWith("integer") || classname.endsWith("long") || classname.endsWith("boolean") || classname.endsWith("bigdecimal")) {
            isBaseType = true;
        }
        List<T> outputList = null;
        try {

            if (rs != null) {


                ResultSetMetaData rsmd = rs.getMetaData();

                Method[] methods = outputClass.getMethods();
                Map<String, Method> methodMap = new HashMap<String, Method>();

                int ii = 0;
                while (rs.next()) {
                    if (ii == 0 && !isBaseType) {
                        for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                            String columnName = rsmd.getColumnName(_iterator + 1).replace("_", "");
                            for (Method method : methods) {
                                if (method.getName().equalsIgnoreCase("set" + columnName)) {
                                    methodMap.put(columnName, method);
                                    break;
                                }
                            }
                        }
                    }
                    if (isOnlyFirst && ii > 0) {
                        break;

                    }

                    T bean = null;
                    if (isBaseType) {

                    } else {
                        bean = (T) outputClass.newInstance();
                    }


                    for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                        if (isBaseType && _iterator > 0) {
                            break;
                        }
                        String columnName = rsmd.getColumnName(_iterator + 1);


                        Object columnValue = rs.getObject(_iterator + 1);

                        if (isBaseType) {
                            if (classname.contains("string")) {
                                bean = (T) rs.getString(_iterator + 1);
                            } else if (classname.contains("int")) {
                                Integer i = Integer.parseInt(rs.getString(_iterator + 1));
                                bean = (T) (i);

                            } else if (classname.contains("long")) {
                                Long i = Long.parseLong(rs.getString(_iterator + 1));
                                bean = (T) (i);
                            } else if (classname.contains("boolean")) {
                                Boolean i = Boolean.parseBoolean(rs.getString(_iterator + 1));
                                bean = (T) (i);
                            } else if (classname.contains("decimal")) {
                                bean = (T) rs.getBigDecimal(_iterator + 1);
                            }

                        } else {
                            Method method = methodMap.get(columnName);
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            if (parameterTypes.length > 0) {
                                String TypeName = parameterTypes[0].getTypeName().toLowerCase();
                                if (TypeName.contains("string")) {
                                    method.invoke(bean, rs.getString(_iterator + 1));
                                } else if (TypeName.contains("int")) {
                                    method.invoke(bean, rs.getInt(_iterator + 1));

                                } else if (TypeName.contains("long")) {
                                    method.invoke(bean, rs.getLong(_iterator + 1));
                                } else if (TypeName.contains("boolean")) {
                                    method.invoke(bean, rs.getBoolean(_iterator + 1));
                                } else if (TypeName.contains("date")) {
                                    method.invoke(bean, LocalDateTime.parse(rs.getString(_iterator + 1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                                } else if (TypeName.contains("decimal")) {
                                    method.invoke(bean, rs.getBigDecimal(_iterator + 1));
                                }

                            }
                        }

                    }
                    if (outputList == null) {
                        outputList = new ArrayList<T>();
                    }
                    outputList.add(bean);
                    ii = ii + 1;
                }


            } else {
                logger.info("rs is null");
                return null;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return outputList;
    }


    public List<Map<String, Object>> mapRersultSetToListMap(ResultSet rs) {

        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        try {
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int ii = 0;
                while (rs.next()) {
                    Map<String, Object> rowmap = new HashMap<String, Object>();
                    for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
                        String columnName = rsmd.getColumnName(_iterator + 1);
                        Object columnValue = rs.getObject(_iterator + 1);
                        rowmap.put(columnName, columnValue);
                    }

                    listmap.add(rowmap);
                    ii = ii + 1;
                }


            } else {
                logger.info("rs is null");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listmap;
    }

    //public boolean invokeMethod()
}