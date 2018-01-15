package com.xianyue.basictype.regex;

import lombok.experimental.UtilityClass;
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
@UtilityClass
public class RegexPiers {

    /**
     * 正则表达式匹配不包含某些字符串
     */
    public Optional<List<String>> findExclueSpecifyRegex(List<String> originStrings) {
        if (CollectionUtils.isEmpty(originStrings)) {
            return Optional.empty();
        }
        Pattern pattern = Pattern.compile("ExternalCall/([^/]*)/([^/]*)/((?!all).*|all.+)");

        findExclueSpecifyRegexPrinter(pattern, originStrings);

        return Optional.of(originStrings.stream().filter(t -> pattern.matcher(t).matches()).collect(Collectors.toList
                ()));
    }

    private void findExclueSpecifyRegexPrinter(Pattern pattern, List<String> originStrings) {
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

    /**
     * 相比于第一种的非贪婪匹配，第二种贪婪匹配的方式更为高效一些
     */
    private void lazyMatch() {
        String url = "/alert/v4/tenants/mi/applications/1/policies/1";

        Pattern pattern1 = Pattern.compile("/alert/v4/tenants/(\\w*?)/.*");
        Matcher matcher1 = pattern1.matcher(url);
        matcher1.matches();
        System.out.println(matcher1.group(1));

        Pattern pattern2 = Pattern.compile("/alert/v4/tenants/([^/]*)/.*");
        Matcher matcher2 = pattern2.matcher(url);
        matcher2.matches();
        System.out.println(matcher2.group(1));
    }

    public static void main(String[] args) {
        lazyMatch();
    }
}
