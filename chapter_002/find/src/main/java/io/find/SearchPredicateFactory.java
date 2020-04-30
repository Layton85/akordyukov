package io.find;

import java.io.File;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * SearchPredicateFactory - factory class for creating predicates depends on specified string mask mode.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SearchPredicateFactory {
    /**
     * Get-method for predicate.
     * @param maskMode - string parameter which influence on resulting predicate.
     * @return - useful BiPredicate.
     */
    public BiPredicate<List<String>, File> getPredicate(String maskMode) {
        BiPredicate<String, String> p = null;
        if ("f".equals(maskMode)) {
            p = (s1, s2) -> (s1.equals(s2));
        } else if ("m".equals(maskMode)) {
            p = (s1, s2) -> (s1.contains(s2));
        } else if ("r".equals(maskMode)) {
            p = (s1, s2) -> (s1.matches(s2));
        }
        final BiPredicate<String, String> pp = p;
        BiPredicate<List<String>, File> predicate = (List<String> maskList, File file) -> {
            boolean result = false;
            if (file.exists() && file.isFile()) {
                for (String mask : maskList) {
                    if (pp.test(file.getName(), mask)) {
                        result = true;
                        break;
                    }
                }
            }
            return result;
        };
        return predicate;
    }
}