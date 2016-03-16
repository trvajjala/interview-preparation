package com.tvajjala.web.config;

import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * The Class DatabaseNamingStrategy will load at deployment time to give the table names dynamically based on Entity names with prefix INNO_.
 */
public class DatabaseNamingStrategy extends ImprovedNamingStrategy {

    /** The Constant PREFIX. */
    private static final String PREFIX = "WF_";

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 5118322121741445924L;

    /**
     * Transforms class names to table names by using the described naming conventions.
     *
     * @param className
     *            the class name
     * @return The constructed table name.
     */
    @Override
    public String classToTableName(final String className) {

        return DatabaseNamingStrategy.addUnderscoredAndCovertToUppercase(DatabaseNamingStrategy.PREFIX
                + StringHelper.unqualify(className).replaceAll("Entity", ""));
    }

    @Override
    public String tableName(final String tableName) {
        return DatabaseNamingStrategy.addUnderscoredAndCovertToUppercase(DatabaseNamingStrategy.PREFIX + tableName);
    }

    /**
     * Adds the underscored and covert to upperCase.
     *
     * @param name
     *            the name
     * @return the string
     */
    static String addUnderscoredAndCovertToUppercase(final String name) {

        return ImprovedNamingStrategy.addUnderscores(name).toLowerCase();
    }
}
