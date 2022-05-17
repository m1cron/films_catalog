package ru.micron.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.CollectionUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageableResponse<T> {

  private List<T> data;
  private PageableData pageable;

  public void add(T item) {
    if (CollectionUtils.isEmpty(data)) {
      data = new ArrayList<>();
    }
    data.add(item);
    if (pageable != null) {
      pageable = new PageableData(
          pageable.getTotalCount() + 1,
          pageable.getPageNum(),
          Math.max(data.size(), pageable.getPageSize()),
          data.size()
      );
    }
  }
}
