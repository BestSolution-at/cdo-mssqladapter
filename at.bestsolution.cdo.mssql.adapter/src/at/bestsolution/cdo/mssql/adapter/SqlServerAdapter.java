/*
* SqlServerAdapter.java
*
* Copyright (c) 2013, BestSolution.at. All rights reserved.
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
* MA 02110-1301 USA
*/
package at.bestsolution.cdo.mssql.adapter;

import java.sql.Driver;

import javax.sql.DataSource;

import net.sourceforge.jtds.jdbcx.JtdsDataSource;

import org.eclipse.net4j.db.DBType;
import org.eclipse.net4j.db.ddl.IDBField;
import org.eclipse.net4j.spi.db.DBAdapter;

/**
 * SqlServerAdapter.
 * 
 * @author martin.bluehweis@bestsolution.at
 */
public class SqlServerAdapter extends DBAdapter {
	/**
	 * name.
	 */
	private static final String NAME = "SqlServer";

	/**
	 * version.
	 */
	public static final String VERSION = "1.0.0";

	/**
	 * sole constructor.
	 */
	public SqlServerAdapter() {
		super( NAME, VERSION );
	}

	/**
	 * getJDBCDriver.
	 * 
	 * @return driver
	 */
	public final Driver getJDBCDriver() {
		return new net.sourceforge.jtds.jdbc.Driver();
	}

	/**
	 * createJDBCDataSource.
	 * 
	 * @return datasource
	 */
	public final DataSource createJDBCDataSource() {
		return new JtdsDataSource();
	}

	@Override
	protected final String getTypeName( final IDBField field ) {
		DBType type = field.getType();
		switch ( type ) {
		case BIT:
			return "SMALLINT";
		case CLOB:
		case LONGVARCHAR:
			return "NTEXT";
		case VARCHAR:
			return "NVARCHAR(4000)";
		case NUMERIC:
			return "DECIMAL";
		case BOOLEAN:
			return "SMALLINT";
		case LONGVARBINARY:
		case BLOB:
			return "VARBINARY(4000)";
		case TIMESTAMP:
			return "DATETIME";
		default:
			break;
		}
		return super.getTypeName( field );
	}

	/**
	 * getReservedWords.
	 * 
	 * @return reserved words.
	 */
	public final String[] getReservedWords() {
		return getSQL92ReservedWords();
	}
}