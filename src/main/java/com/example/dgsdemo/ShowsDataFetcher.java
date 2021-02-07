package com.example.dgsdemo;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.stream.Collectors;

@DgsComponent
public class ShowsDataFetcher {

  private final List<Show> shows = List.of(
      new Show("Stranger Things", 2016),
      new Show("Ozark", 2017),
      new Show("The Crown", 2016),
      new Show("Dead to Me", 2019),
      new Show("Orange is the New Black", 2013)
  );

  @DgsData(parentType = "Query", field = "shows")
  public List<Show> shows(@InputArgument("titleFilter") String titleFilter) {
    if (titleFilter == null) {
      return shows;
    }

    return shows.stream().filter(s -> s.getTitle().contains(titleFilter))
        .collect(Collectors.toList());
  }
}

