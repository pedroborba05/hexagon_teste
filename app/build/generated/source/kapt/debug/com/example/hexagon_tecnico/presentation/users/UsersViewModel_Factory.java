package com.example.hexagon_tecnico.presentation.users;

import com.example.hexagon_tecnico.domain.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class UsersViewModel_Factory implements Factory<UsersViewModel> {
  private final Provider<UserRepository> repoProvider;

  public UsersViewModel_Factory(Provider<UserRepository> repoProvider) {
    this.repoProvider = repoProvider;
  }

  @Override
  public UsersViewModel get() {
    return newInstance(repoProvider.get());
  }

  public static UsersViewModel_Factory create(Provider<UserRepository> repoProvider) {
    return new UsersViewModel_Factory(repoProvider);
  }

  public static UsersViewModel newInstance(UserRepository repo) {
    return new UsersViewModel(repo);
  }
}
