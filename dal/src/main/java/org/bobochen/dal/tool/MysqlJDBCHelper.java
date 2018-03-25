package org.bobochen.dal.tool;

import com.alibaba.druid.pool.DruidDataSource;
import org.bobochen.common.Page;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class MysqlJDBCHelper {
    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        dataSource.setUrl("jdbc:mysql://192.168.178.128:3306/gotest?charset=utf8");
        dataSource.setInitialSize(50);
        dataSource.setMinIdle(50);
        dataSource.setMaxActive(1000);
        // 启用监控统计功能
        // dataSource.setFilters("stat");
        // for mysql
        dataSource.setPoolPreparedStatements(false);
    }

    public static Connection getConnection() {
        try {


            return dataSource.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> queryList(String sql, Class cla) {
        return queryList(sql, cla, null);
    }

    public static <T> List<T> queryList(String sql, Class cla, List<SqlParam> params) {
        Connection con = null;
        try {
            con = getConnection();
            OddiffResultSetMapper mapper = new OddiffResultSetMapper();
            //Statement st = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                int i = 0;
                for (SqlParam para : params) {
                    switch (para.getType()) {
                        case StringDb:
                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case DateTimeDb:




                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case IntDb:

                            preparedStatement.setInt(i, Integer.parseInt(para.getObj().toString()));
                            break;
                        case LongDb:
                            preparedStatement.setLong(i, Long.parseLong(para.getObj().toString()));
                            break;
                        case BooleanDb:
                            preparedStatement.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                            break;
                        case DecimalDb:
                            preparedStatement.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                            break;
                        default:
                            break;
                    }

                    i = i + 1;
                }
            }
            //preparedStatement.setd
            //5 执行sql
            ResultSet rs = preparedStatement.executeQuery();
            return mapper.mapRersultSetToObject(rs, cla, false);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Page<Map<String, Object>> queryListMapPage(String sql, Class cla, long pageIndex, long pageSize) {
        return queryListMapPage(sql, cla, pageIndex, pageSize);
    }

    public static Page<Map<String, Object>> queryListMapPage(String sql, Class cla, long pageIndex, long pageSize, List<SqlParam> params) {
        Connection con = null;
        Page<Map<String,Object>> page = new Page<Map<String,Object>>();
        try {
            con = getConnection();


            long skipcount = (pageIndex - 1) * pageSize;
            String countsql = "select count(1) from (" + sql + ") a";


            OddiffResultSetMapper mapper = new OddiffResultSetMapper();

            PreparedStatement preparedStatement = con.prepareStatement(countsql);
            if (params != null && params.size() > 0) {
                int i = 0;
                for (SqlParam para : params) {
                    switch (para.getType()) {
                        case StringDb:
                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case DateTimeDb:

                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case IntDb:

                            preparedStatement.setInt(i, Integer.parseInt(para.getObj().toString()));
                            break;
                        case LongDb:
                            preparedStatement.setLong(i, Long.parseLong(para.getObj().toString()));
                            break;
                        case BooleanDb:
                            preparedStatement.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                            break;
                        case DecimalDb:
                            preparedStatement.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                            break;
                        default:
                            break;
                    }

                    i = i + 1;
                }
            }
            int count = 0;

            ResultSet rscount = preparedStatement.executeQuery();

            while (rscount.next()) {
                count = rscount.getInt(1);
            }
            if (count > 0) {
                page.setCurrentPage(pageIndex);
                page.setItemsPerPage(pageSize);
                page.setTotalItems(count);
                page.setTotalPages((count / pageSize) + 1);
                String datsql = sql + " limit " + skipcount + "," + pageSize;

                OddiffResultSetMapper mapperd = new OddiffResultSetMapper();
                //Statement st = con.createStatement();
                PreparedStatement preparedStatementd = con.prepareStatement(datsql);
                if (params != null && params.size() > 0) {
                    int i = 0;
                    for (SqlParam para : params) {
                        switch (para.getType()) {
                            case StringDb:
                                preparedStatementd.setString(i, para.getObj().toString());
                                break;
                            case DateTimeDb:

                                preparedStatementd.setString(i, para.getObj().toString());
                                break;
                            case IntDb:

                                preparedStatementd.setInt(i, Integer.parseInt(para.getObj().toString()));
                                break;
                            case LongDb:
                                preparedStatementd.setLong(i, Long.parseLong(para.getObj().toString()));
                                break;
                            case BooleanDb:
                                preparedStatementd.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                                break;
                            case DecimalDb:
                                preparedStatementd.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                                break;
                            default:
                                break;
                        }

                        i = i + 1;
                    }
                }
                //preparedStatement.setd
                //5 执行sql
                ResultSet rs = preparedStatementd.executeQuery();
                List<Map<String, Object>> lm = mapper.mapRersultSetToListMap(rs);
                page.setItems(lm);

            }
            return page;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static List<Map<String, Object>> queryListMap(String sql, Class cla) {
        return queryList(sql, cla, null);
    }

    public static List<Map<String, Object>> queryListMap(String sql, Class cla, List<SqlParam> params) {
        Connection con = null;
        try {
            con = getConnection();
            OddiffResultSetMapper mapper = new OddiffResultSetMapper();
            //Statement st = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                int i = 0;
                for (SqlParam para : params) {
                    switch (para.getType()) {
                        case StringDb:
                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case DateTimeDb:

                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case IntDb:

                            preparedStatement.setInt(i, Integer.parseInt(para.getObj().toString()));
                            break;
                        case LongDb:
                            preparedStatement.setLong(i, Long.parseLong(para.getObj().toString()));
                            break;
                        case BooleanDb:
                            preparedStatement.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                            break;
                        case DecimalDb:
                            preparedStatement.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                            break;
                        default:
                            break;
                    }

                    i = i + 1;
                }
            }
            //preparedStatement.setd
            //5 执行sql
            ResultSet rs = preparedStatement.executeQuery();
            return mapper.mapRersultSetToListMap(rs);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> T queryObject(String sql, Class cla) {
        return queryObject(sql, cla, null);
    }

    public static <T> T queryObject(String sql, Class cla, List<SqlParam> params) {
        Connection con = null;
        try {
            con = getConnection();
            OddiffResultSetMapper mapper = new OddiffResultSetMapper();
            //Statement st = con.createStatement();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                int i = 0;
                for (SqlParam para : params) {
                    switch (para.getType()) {
                        case StringDb:
                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case DateTimeDb:

                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case IntDb:

                            preparedStatement.setInt(i, Integer.parseInt(para.getObj().toString()));
                            break;
                        case LongDb:
                            preparedStatement.setLong(i, Long.parseLong(para.getObj().toString()));
                            break;
                        case BooleanDb:
                            preparedStatement.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                            break;
                        case DecimalDb:
                            preparedStatement.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                            break;
                        default:
                            break;
                    }

                    i = i + 1;
                }
            }
            //preparedStatement.setd
            //5 执行sql
            ResultSet rs = preparedStatement.executeQuery();
            List<T> list = mapper.mapRersultSetToObject(rs, cla, false);
            if (list != null && list.size() > 0) {
                return list.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> Page<T> queryPage(String sql, Class cla, long pageIndex, long pageSize) {
        return queryPage(sql, cla, pageIndex, pageSize);
    }

    public static <T> Page<T> queryPage(String sql, Class cla, long pageIndex, long pageSize, List<SqlParam> params) {
        Connection con = null;
        Page<T> page = new Page<T>();
        try {


            con = getConnection();
            long skipcount = (pageIndex - 1) * pageSize;
            String countsql = "select count(1) from (" + sql + ") a";


            OddiffResultSetMapper mapper = new OddiffResultSetMapper();

            PreparedStatement preparedStatement = con.prepareStatement(countsql);
            if (params != null && params.size() > 0) {
                int i = 0;
                for (SqlParam para : params) {
                    switch (para.getType()) {
                        case StringDb:
                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case DateTimeDb:

                            preparedStatement.setString(i, para.getObj().toString());
                            break;
                        case IntDb:

                            preparedStatement.setInt(i, Integer.parseInt(para.getObj().toString()));
                            break;
                        case LongDb:
                            preparedStatement.setLong(i, Long.parseLong(para.getObj().toString()));
                            break;
                        case BooleanDb:
                            preparedStatement.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                            break;
                        case DecimalDb:
                            preparedStatement.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                            break;
                        default:
                            break;
                    }

                    i = i + 1;
                }
            }
            int count = 0;

            ResultSet rscount = preparedStatement.executeQuery();

            while (rscount.next()) {
                count = rscount.getInt(1);
            }
            if (count > 0) {
                page.setCurrentPage(pageIndex);
                page.setItemsPerPage(pageSize);
                page.setTotalItems(count);
                page.setTotalPages((count / pageSize) + 1);
                String datsql = sql + " limit " + skipcount + "," + pageSize;
                PreparedStatement preparedStatementdata = con.prepareStatement(datsql);
                if (params != null && params.size() > 0) {
                    int i = 0;
                    for (SqlParam para : params) {
                        switch (para.getType()) {
                            case StringDb:
                                preparedStatementdata.setString(i, para.getObj().toString());
                                break;
                            case DateTimeDb:

                                preparedStatementdata.setString(i, para.getObj().toString());
                                break;
                            case IntDb:

                                preparedStatementdata.setInt(i, Integer.parseInt(para.getObj().toString()));
                                break;
                            case LongDb:
                                preparedStatementdata.setLong(i, Long.parseLong(para.getObj().toString()));
                                break;
                            case BooleanDb:
                                preparedStatementdata.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                                break;
                            case DecimalDb:
                                preparedStatementdata.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                                break;
                            default:
                                break;
                        }

                        i = i + 1;
                    }
                }
                ResultSet rscountdata = preparedStatement.executeQuery();
                page.setItems(mapper.mapRersultSetToObject(rscountdata, cla, false));
            }

            return page;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void excute(String sql) {
        excute(sql, null);
    }

    public static void excute(String sql, List<SqlParam> params) {
        Connection con = null;


        try {
            con = getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            if (params != null && params.size() > 0) {
                int i = 0;
                for (SqlParam para : params) {
                    switch (para.getType()) {
                        case StringDb:
                            pstmt.setString(i, para.getObj().toString());
                            break;
                        case DateTimeDb:

                            pstmt.setString(i, para.getObj().toString());
                            break;
                        case IntDb:

                            pstmt.setInt(i, Integer.parseInt(para.getObj().toString()));
                            break;
                        case LongDb:
                            pstmt.setLong(i, Long.parseLong(para.getObj().toString()));
                            break;
                        case BooleanDb:
                            pstmt.setBoolean(i, Boolean.parseBoolean(para.getObj().toString()));
                            break;
                        case DecimalDb:
                            pstmt.setBigDecimal(i, new BigDecimal(para.getObj().toString()));
                            break;
                        default:
                            break;
                    }

                    i = i + 1;
                }
            }
            int i = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
