package br.com.cadmus.supply_transport.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListUtils {

    @SafeVarargs
    public static <E> List<E> concatenarListas(List<? extends E>... listas) {
        return Stream.of(listas)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
