package com.xianyue.basictype.regex;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Xianyue
 *         正则表达式的基本使用
 */
public class RegexPiers {

    /**
     * 正则表达式匹配不包含某些字符串
     */
    public static Optional<List<String>> findExclueSpecifyRegex(List<String> originStrings) {
        if (CollectionUtils.isEmpty(originStrings)) {
            return Optional.empty();
        }
        Pattern pattern = Pattern.compile("ExternalCall/([^/]*)/([^/]*)/((?!all).*|all.+)");

        findExclueSpecifyRegexPrinter(pattern, originStrings);

        return Optional.of(originStrings.stream().filter(t -> pattern.matcher(t).matches()).collect(Collectors.toList
                ()));


    }

    private static void findExclueSpecifyRegexPrinter(Pattern pattern, List<String> originStrings) {
        Matcher matcher;
        for (String str : originStrings) {
            matcher = pattern.matcher(str);
            if (matcher.matches()) {
                System.out.println("origin " + str);
                System.out.println(String.format("g0:{%s},g1:{%s}, g2:{%s},g3:{%s}", matcher.group(0), matcher.group
                        (1), matcher.group(2), matcher.group(3)));
            }
        }
    }
}
