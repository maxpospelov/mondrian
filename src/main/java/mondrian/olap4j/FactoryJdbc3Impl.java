/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2007-2011 Pentaho
// All Rights Reserved.
*/
package mondrian.olap4j;

import mondrian.rolap.RolapConnection;

import org.olap4j.OlapException;

import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * Implementation of {@link mondrian.olap4j.Factory} for JDBC 3.0.
 *
 * @author jhyde
 * @since Jun 14, 2007
 */
class FactoryJdbc3Impl implements Factory {
    private CatalogFinder catalogFinder;

    public Connection newConnection(
        MondrianOlap4jDriver driver,
        String url,
        Properties info)
        throws SQLException
    {
        return new MondrianOlap4jConnectionJdbc3(driver, url, info);
    }

    public EmptyResultSet newEmptyResultSet(
        MondrianOlap4jConnection olap4jConnection)
    {
        List<String> headerList = Collections.emptyList();
        List<List<Object>> rowList = Collections.emptyList();
        return new EmptyResultSetJdbc3(
            olap4jConnection, headerList, rowList);
    }

    public ResultSet newFixedResultSet(
        MondrianOlap4jConnection olap4jConnection,
        List<String> headerList,
        List<List<Object>> rowList)
    {
        return new EmptyResultSetJdbc3(
            olap4jConnection, headerList, rowList);
    }

    public MondrianOlap4jCellSet newCellSet(
        MondrianOlap4jStatement olap4jStatement)
    {
        return new MondrianOlap4jCellSetJdbc3(olap4jStatement);
    }

    public MondrianOlap4jStatement newStatement(
        MondrianOlap4jConnection olap4jConnection)
    {
        return new MondrianOlap4jStatementJdbc3(olap4jConnection);
    }

    public MondrianOlap4jPreparedStatement newPreparedStatement(
        String mdx,
        MondrianOlap4jConnection olap4jConnection)
        throws OlapException
    {
        return new MondrianOlap4jPreparedStatementJdbc3(olap4jConnection, mdx);
    }

    public MondrianOlap4jDatabaseMetaData newDatabaseMetaData(
        MondrianOlap4jConnection olap4jConnection,
        RolapConnection mondrianConnection)
    {
        return new MondrianOlap4jDatabaseMetaDataJdbc3(
            olap4jConnection, mondrianConnection);
    }

    // Inner classes

    private static class MondrianOlap4jStatementJdbc3
        extends MondrianOlap4jStatement
    {
        public MondrianOlap4jStatementJdbc3(
            MondrianOlap4jConnection olap4jConnection)
        {
            super(olap4jConnection);
        }

        public void closeOnCompletion() throws SQLException {

        }

        public boolean isCloseOnCompletion() throws SQLException {
            return false;
        }
    }

    private static class MondrianOlap4jPreparedStatementJdbc3
        extends MondrianOlap4jPreparedStatement
    {
        public MondrianOlap4jPreparedStatementJdbc3(
            MondrianOlap4jConnection olap4jConnection,
            String mdx)
            throws OlapException
        {
            super(olap4jConnection, mdx);
        }

        public void setRowId(int i, RowId rowId) throws SQLException {

        }

        public void setNString(int i, String s) throws SQLException {

        }

        public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {

        }

        public void setNClob(int i, NClob nClob) throws SQLException {

        }

        public void setClob(int i, Reader reader, long l) throws SQLException {

        }

        public void setBlob(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void setNClob(int i, Reader reader, long l) throws SQLException {

        }

        public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {

        }

        public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void setCharacterStream(int i, Reader reader, long l) throws SQLException {

        }

        public void setAsciiStream(int i, InputStream inputStream) throws SQLException {

        }

        public void setBinaryStream(int i, InputStream inputStream) throws SQLException {

        }

        public void setCharacterStream(int i, Reader reader) throws SQLException {

        }

        public void setNCharacterStream(int i, Reader reader) throws SQLException {

        }

        public void setClob(int i, Reader reader) throws SQLException {

        }

        public void setBlob(int i, InputStream inputStream) throws SQLException {

        }

        public void setNClob(int i, Reader reader) throws SQLException {

        }

        public void closeOnCompletion() throws SQLException {

        }

        public boolean isCloseOnCompletion() throws SQLException {
            return false;
        }
    }

    private static class MondrianOlap4jCellSetJdbc3
        extends MondrianOlap4jCellSet
    {
        public MondrianOlap4jCellSetJdbc3(
            MondrianOlap4jStatement olap4jStatement)
        {
            super(olap4jStatement);
        }

        public RowId getRowId(int i) throws SQLException {
            return null;
        }

        public RowId getRowId(String s) throws SQLException {
            return null;
        }

        public void updateRowId(int i, RowId rowId) throws SQLException {

        }

        public void updateRowId(String s, RowId rowId) throws SQLException {

        }

        public int getHoldability() throws SQLException {
            return 0;
        }

        public boolean isClosed() throws SQLException {
            return false;
        }

        public void updateNString(int i, String s) throws SQLException {

        }

        public void updateNString(String s, String s1) throws SQLException {

        }

        public void updateNClob(int i, NClob nClob) throws SQLException {

        }

        public void updateNClob(String s, NClob nClob) throws SQLException {

        }

        public NClob getNClob(int i) throws SQLException {
            return null;
        }

        public NClob getNClob(String s) throws SQLException {
            return null;
        }

        public SQLXML getSQLXML(int i) throws SQLException {
            return null;
        }

        public SQLXML getSQLXML(String s) throws SQLException {
            return null;
        }

        public void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {

        }

        public void updateSQLXML(String s, SQLXML sqlxml) throws SQLException {

        }

        public String getNString(int i) throws SQLException {
            return null;
        }

        public String getNString(String s) throws SQLException {
            return null;
        }

        public Reader getNCharacterStream(int i) throws SQLException {
            return null;
        }

        public Reader getNCharacterStream(String s) throws SQLException {
            return null;
        }

        public void updateNCharacterStream(int i, Reader reader, long l) throws SQLException {

        }

        public void updateNCharacterStream(String s, Reader reader, long l) throws SQLException {

        }

        public void updateAsciiStream(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void updateBinaryStream(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void updateCharacterStream(int i, Reader reader, long l) throws SQLException {

        }

        public void updateAsciiStream(String s, InputStream inputStream, long l) throws SQLException {

        }

        public void updateBinaryStream(String s, InputStream inputStream, long l) throws SQLException {

        }

        public void updateCharacterStream(String s, Reader reader, long l) throws SQLException {

        }

        public void updateBlob(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void updateBlob(String s, InputStream inputStream, long l) throws SQLException {

        }

        public void updateClob(int i, Reader reader, long l) throws SQLException {

        }

        public void updateClob(String s, Reader reader, long l) throws SQLException {

        }

        public void updateNClob(int i, Reader reader, long l) throws SQLException {

        }

        public void updateNClob(String s, Reader reader, long l) throws SQLException {

        }

        public void updateNCharacterStream(int i, Reader reader) throws SQLException {

        }

        public void updateNCharacterStream(String s, Reader reader) throws SQLException {

        }

        public void updateAsciiStream(int i, InputStream inputStream) throws SQLException {

        }

        public void updateBinaryStream(int i, InputStream inputStream) throws SQLException {

        }

        public void updateCharacterStream(int i, Reader reader) throws SQLException {

        }

        public void updateAsciiStream(String s, InputStream inputStream) throws SQLException {

        }

        public void updateBinaryStream(String s, InputStream inputStream) throws SQLException {

        }

        public void updateCharacterStream(String s, Reader reader) throws SQLException {

        }

        public void updateBlob(int i, InputStream inputStream) throws SQLException {

        }

        public void updateBlob(String s, InputStream inputStream) throws SQLException {

        }

        public void updateClob(int i, Reader reader) throws SQLException {

        }

        public void updateClob(String s, Reader reader) throws SQLException {

        }

        public void updateNClob(int i, Reader reader) throws SQLException {

        }

        public void updateNClob(String s, Reader reader) throws SQLException {

        }

        public <T> T getObject(int i, Class<T> aClass) throws SQLException {
            return null;
        }

        public <T> T getObject(String s, Class<T> aClass) throws SQLException {
            return null;
        }
    }

    private static class EmptyResultSetJdbc3 extends EmptyResultSet {
        public EmptyResultSetJdbc3(
            MondrianOlap4jConnection olap4jConnection,
            List<String> headerList,
            List<List<Object>> rowList)
        {
            super(olap4jConnection, headerList, rowList);
        }

        public RowId getRowId(int i) throws SQLException {
            return null;
        }

        public RowId getRowId(String s) throws SQLException {
            return null;
        }

        public void updateRowId(int i, RowId rowId) throws SQLException {

        }

        public void updateRowId(String s, RowId rowId) throws SQLException {

        }

        public int getHoldability() throws SQLException {
            return 0;
        }

        public boolean isClosed() throws SQLException {
            return false;
        }

        public void updateNString(int i, String s) throws SQLException {

        }

        public void updateNString(String s, String s1) throws SQLException {

        }

        public void updateNClob(int i, NClob nClob) throws SQLException {

        }

        public void updateNClob(String s, NClob nClob) throws SQLException {

        }

        public NClob getNClob(int i) throws SQLException {
            return null;
        }

        public NClob getNClob(String s) throws SQLException {
            return null;
        }

        public SQLXML getSQLXML(int i) throws SQLException {
            return null;
        }

        public SQLXML getSQLXML(String s) throws SQLException {
            return null;
        }

        public void updateSQLXML(int i, SQLXML sqlxml) throws SQLException {

        }

        public void updateSQLXML(String s, SQLXML sqlxml) throws SQLException {

        }

        public String getNString(int i) throws SQLException {
            return null;
        }

        public String getNString(String s) throws SQLException {
            return null;
        }

        public Reader getNCharacterStream(int i) throws SQLException {
            return null;
        }

        public Reader getNCharacterStream(String s) throws SQLException {
            return null;
        }

        public void updateNCharacterStream(int i, Reader reader, long l) throws SQLException {

        }

        public void updateNCharacterStream(String s, Reader reader, long l) throws SQLException {

        }

        public void updateAsciiStream(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void updateBinaryStream(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void updateCharacterStream(int i, Reader reader, long l) throws SQLException {

        }

        public void updateAsciiStream(String s, InputStream inputStream, long l) throws SQLException {

        }

        public void updateBinaryStream(String s, InputStream inputStream, long l) throws SQLException {

        }

        public void updateCharacterStream(String s, Reader reader, long l) throws SQLException {

        }

        public void updateBlob(int i, InputStream inputStream, long l) throws SQLException {

        }

        public void updateBlob(String s, InputStream inputStream, long l) throws SQLException {

        }

        public void updateClob(int i, Reader reader, long l) throws SQLException {

        }

        public void updateClob(String s, Reader reader, long l) throws SQLException {

        }

        public void updateNClob(int i, Reader reader, long l) throws SQLException {

        }

        public void updateNClob(String s, Reader reader, long l) throws SQLException {

        }

        public void updateNCharacterStream(int i, Reader reader) throws SQLException {

        }

        public void updateNCharacterStream(String s, Reader reader) throws SQLException {

        }

        public void updateAsciiStream(int i, InputStream inputStream) throws SQLException {

        }

        public void updateBinaryStream(int i, InputStream inputStream) throws SQLException {

        }

        public void updateCharacterStream(int i, Reader reader) throws SQLException {

        }

        public void updateAsciiStream(String s, InputStream inputStream) throws SQLException {

        }

        public void updateBinaryStream(String s, InputStream inputStream) throws SQLException {

        }

        public void updateCharacterStream(String s, Reader reader) throws SQLException {

        }

        public void updateBlob(int i, InputStream inputStream) throws SQLException {

        }

        public void updateBlob(String s, InputStream inputStream) throws SQLException {

        }

        public void updateClob(int i, Reader reader) throws SQLException {

        }

        public void updateClob(String s, Reader reader) throws SQLException {

        }

        public void updateNClob(int i, Reader reader) throws SQLException {

        }

        public void updateNClob(String s, Reader reader) throws SQLException {

        }

        public <T> T getObject(int i, Class<T> aClass) throws SQLException {
            return null;
        }

        public <T> T getObject(String s, Class<T> aClass) throws SQLException {
            return null;
        }
    }

    private class MondrianOlap4jConnectionJdbc3
        extends MondrianOlap4jConnection
    {
        public MondrianOlap4jConnectionJdbc3(
            MondrianOlap4jDriver driver,
            String url,
            Properties info) throws SQLException
        {
            super(FactoryJdbc3Impl.this, driver, url, info);
        }

        public Clob createClob() throws SQLException {
            return null;
        }

        public Blob createBlob() throws SQLException {
            return null;
        }

        public NClob createNClob() throws SQLException {
            return null;
        }

        public SQLXML createSQLXML() throws SQLException {
            return null;
        }

        public boolean isValid(int i) throws SQLException {
            return false;
        }

        public void setClientInfo(String s, String s1) throws SQLClientInfoException {

        }

        public void setClientInfo(Properties properties) throws SQLClientInfoException {

        }

        public String getClientInfo(String s) throws SQLException {
            return null;
        }

        public Properties getClientInfo() throws SQLException {
            return null;
        }

        public Array createArrayOf(String s, Object[] objects) throws SQLException {
            return null;
        }

        public Struct createStruct(String s, Object[] objects) throws SQLException {
            return null;
        }

        public void abort(Executor executor) throws SQLException {

        }

        public void setNetworkTimeout(Executor executor, int i) throws SQLException {

        }

        public int getNetworkTimeout() throws SQLException {
            return 0;
        }
    }

    private static class MondrianOlap4jDatabaseMetaDataJdbc3
        extends MondrianOlap4jDatabaseMetaData
    {
        public MondrianOlap4jDatabaseMetaDataJdbc3(
            MondrianOlap4jConnection olap4jConnection,
            RolapConnection mondrianConnection)
        {
            super(olap4jConnection, mondrianConnection);
        }

        public RowIdLifetime getRowIdLifetime() throws SQLException {
            return null;
        }

        public ResultSet getSchemas(String s, String s1) throws SQLException {
            return null;
        }

        public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
            return false;
        }

        public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
            return false;
        }

        public ResultSet getClientInfoProperties() throws SQLException {
            return null;
        }

        public ResultSet getFunctions(String s, String s1, String s2) throws SQLException {
            return null;
        }

        public ResultSet getFunctionColumns(String s, String s1, String s2, String s3) throws SQLException {
            return null;
        }

        public ResultSet getPseudoColumns(String s, String s1, String s2, String s3) throws SQLException {
            return null;
        }

        public boolean generatedKeyAlwaysReturned() throws SQLException {
            return false;
        }
    }
}

// End FactoryJdbc3Impl.java
