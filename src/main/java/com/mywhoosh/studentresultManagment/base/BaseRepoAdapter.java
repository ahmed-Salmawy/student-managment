package com.mywhoosh.studentresultManagment.base;

import java.util.Optional;

public interface BaseRepoAdapter  <D extends AbstractBaseDto>{

      String create(D dto);
      void update(D dto);
      void delete(D dto);
      Optional<D> retrieve(String id);
}
