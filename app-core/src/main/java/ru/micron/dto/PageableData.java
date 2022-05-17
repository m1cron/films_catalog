package ru.micron.dto;

import javax.validation.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageableData {

  private long totalCount;
  private long totalPages;
  private long pageNum;
  private long pageSize;
  private long currentPageSize;

  public PageableData(long totalCount, long pageNum, long pageSize, long currentPageSize) {
    validateMinValue(pageNum, 1);
    validateMinValue(pageSize, 1);
    validateMinValue(totalCount, 0);

    this.totalCount = totalCount;
    this.pageNum = pageNum;
    this.pageSize = pageSize;
    this.totalPages =
        totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
    this.currentPageSize = currentPageSize;
  }

  private void validateMinValue(long value, int minValue) {
    if (value < minValue) {
      throw new ValidationException(
          String.format("The current value [%s] is less than min possible value [%s] ", value,
              minValue));
    }
  }

}
