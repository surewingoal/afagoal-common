package com.afagoal.utils.db;

import org.hibernate.HibernateException;
import org.hibernate.property.access.internal.PropertyAccessStrategyBasicImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyChainedImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyFieldImpl;
import org.hibernate.property.access.internal.PropertyAccessStrategyMapImpl;
import org.hibernate.property.access.spi.Setter;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by BaoCai on 18/5/29.
 * Description:
 */
public class CustomAliasResultTransformer extends AliasedTupleSubsetResultTransformer {

    private final Class resultClass;
    private boolean isInitialized;
    private String[] aliases;
    private Setter[] setters;

    public CustomAliasResultTransformer(Class resultClass) {
        if (resultClass == null) {
            throw new IllegalArgumentException("resultClass cannot be null");
        }
        isInitialized = false;
        this.resultClass = resultClass;
    }

    @Override
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Object result;

        try {
            if (!isInitialized) {
                initialize(aliases);
            } else {
                check(aliases);
            }

            result = resultClass.newInstance();

            for (int i = 0; i < aliases.length; i++) {
                if (setters[i] != null) {
                    Object object = tuple[i];
                    Setter setter = setters[i];
                    Class paramClass = setter.getMethod().getParameterTypes()[0];
                    if (null != object && !paramClass.isInstance(object)) {
                        if (object instanceof Timestamp && paramClass.equals(LocalDateTime.class)) {
                            //datetime
                            Timestamp timestamp = (Timestamp) object;
                            LocalDateTime localDateTime = timestamp.toLocalDateTime();
                            setters[i].set(result, localDateTime, null);
                        } else if (object instanceof Date && paramClass.equals(LocalDate.class)) {
                            //date
                            Date dateObj = (Date) object;
                            LocalDate localDate = dateObj.toLocalDate();
                            setters[i].set(result, localDate, null);
                        } else if (object instanceof Number) {
                            //NUM
                            Object number = null;
                            Number numberObj = ((Number) object);
                            if (paramClass.equals(Integer.class)) {
                                number = numberObj.intValue();
                            } else if (paramClass.equals(Byte.class)) {
                                number = numberObj.byteValue();
                            } else if (paramClass.equals(Long.class)) {
                                number = numberObj.longValue();
                            } else if (paramClass.equals(Float.class)) {
                                number = numberObj.floatValue();
                            } else if (paramClass.equals(Double.class)) {
                                number = numberObj.doubleValue();
                            }
                            setters[i].set(result, number, null);
                        } else {
                            throw new RuntimeException("sql transformer error : required type:"
                                    + paramClass.getName() + ";but find type :" + object.getClass().getName());
                        }
                    } else {
                        setters[i].set(result, tuple[i], null);
                    }

                }
            }
        } catch (InstantiationException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        } catch (IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        }

        return result;
    }

    private void initialize(String[] aliases) {
        PropertyAccessStrategyChainedImpl propertyAccessStrategy = new PropertyAccessStrategyChainedImpl(
                PropertyAccessStrategyBasicImpl.INSTANCE,
                PropertyAccessStrategyFieldImpl.INSTANCE,
                PropertyAccessStrategyMapImpl.INSTANCE
        );
        this.aliases = new String[aliases.length];
        setters = new Setter[aliases.length];
        for (int i = 0; i < aliases.length; i++) {
            String alias = aliases[i];
            if (alias != null) {
                this.aliases[i] = alias;
                setters[i] = propertyAccessStrategy.buildPropertyAccess(resultClass, alias).getSetter();
            }
        }
        isInitialized = true;
    }

    private void check(String[] aliases) {
        if (!Arrays.equals(aliases, this.aliases)) {
            throw new IllegalStateException(
                    "aliases are different from what is cached; aliases=" + Arrays.asList(aliases) +
                            " cached=" + Arrays.asList(this.aliases));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomAliasResultTransformer that = (CustomAliasResultTransformer) o;

        if (!resultClass.equals(that.resultClass)) {
            return false;
        }
        if (!Arrays.equals(aliases, that.aliases)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = resultClass.hashCode();
        result = 31 * result + (aliases != null ? Arrays.hashCode(aliases) : 0);
        return result;
    }
}
