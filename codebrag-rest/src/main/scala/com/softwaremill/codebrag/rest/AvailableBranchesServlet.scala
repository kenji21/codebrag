package com.softwaremill.codebrag.rest

import com.softwaremill.codebrag.service.user.Authenticator
import com.typesafe.scalalogging.slf4j.Logging
import com.softwaremill.codebrag.cache.{RepositoriesCache, RepositoryCache}

class AvailableBranchesServlet(val authenticator: Authenticator, repositoriesCache: RepositoriesCache) extends JsonServletWithAuthentication with Logging {

  get("/:repo") {
    val repo = repositoriesCache.getRepo(params("repo"))
    val branches = repo.getShortBranchNames.toList.sorted
    Map("branches" -> branches, "current" -> repo.getCheckedOutBranchShortName, "repoType" -> repo.repository.repoData.repoType)
  }

}

object AvailableBranchesServlet {
  val MountPath = "branches"
}