package com.akriti.meeshoapp.model

import com.google.gson.annotations.SerializedName

data class GithubPullRequestsServiceResponse (
    //@SerializedName("url") val url : String,
    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("diff_url") val diff_url : String,
    @SerializedName("patch_url") val patch_url : String,
    @SerializedName("issue_url") val issue_url : String,
    @SerializedName("commits_url") val commits_url : String,
    @SerializedName("review_comments_url") val review_comments_url : String,
    @SerializedName("review_comment_url") val review_comment_url : String,
    @SerializedName("comments_url") val comments_url : String,
    @SerializedName("statuses_url") val statuses_url : String,
    @SerializedName("number") val number : Int,
    @SerializedName("state") val state : String,
    @SerializedName("locked") val locked : Boolean,
    @SerializedName("title") val title : String,
    @SerializedName("user") val user : User,
    @SerializedName("body") val body : String,
    @SerializedName("labels") val labels : List<Label>,
    @SerializedName("milestone") val milestone : Milestone,
    @SerializedName("active_lock_reason") val active_lock_reason : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("closed_at") val closed_at : String,
    @SerializedName("merged_at") val merged_at : String,
    @SerializedName("merge_commit_sha") val merge_commit_sha : String,
    @SerializedName("assignee") val assignee : User,
    @SerializedName("assignees") val assignees : List<User>,
    @SerializedName("requested_reviewers") val requested_reviewers : List<User>,
    @SerializedName("requested_teams") val requested_teams : List<Teams>,
    @SerializedName("head") val head : Base,
    @SerializedName("base") val base : Base,
    @SerializedName("_links") val _links : Links,
    @SerializedName("author_association") val author_association : String,
    @SerializedName("auto_merge") val auto_merge : String,
    @SerializedName("draft") val draft : Boolean
)

data class User (

    @SerializedName("login") val login : String?,
    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("avatar_url") val avatar_url : String,
    @SerializedName("gravatar_id") val gravatar_id : String,
    @SerializedName("url") val url : String,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("followers_url") val followers_url : String,
    @SerializedName("following_url") val following_url : String,
    @SerializedName("gists_url") val gists_url : String,
    @SerializedName("starred_url") val starred_url : String,
    @SerializedName("subscriptions_url") val subscriptions_url : String,
    @SerializedName("organizations_url") val organizations_url : String,
    @SerializedName("repos_url") val repos_url : String,
    @SerializedName("events_url") val events_url : String,
    @SerializedName("received_events_url") val received_events_url : String,
    @SerializedName("type") val type : String,
    @SerializedName("site_admin") val site_admin : Boolean
)

data class Milestone (

    @SerializedName("url") val url : String,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("labels_url") val labels_url : String,
    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("number") val number : Int,
    @SerializedName("state") val state : String,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("creator") val creator : User,
    @SerializedName("open_issues") val open_issues : Int,
    @SerializedName("closed_issues") val closed_issues : Int,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("closed_at") val closed_at : String,
    @SerializedName("due_on") val due_on : String
)

data class Base (

    @SerializedName("label") val label : String,
    @SerializedName("ref") val ref : String,
    @SerializedName("sha") val sha : String,
    @SerializedName("user") val user : User,
    @SerializedName("repo") val repo : Repo
)

data class Label (

    @SerializedName("id") val id : Long,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("url") val url : String,
    @SerializedName("name") val name : String,
    @SerializedName("description") val description : String,
    @SerializedName("color") val color : String,
    @SerializedName("default") val default : Boolean
)

data class Links (

    @SerializedName("self") val self : Link,
    @SerializedName("html") val html : Link,
    @SerializedName("issue") val issue : Link,
    @SerializedName("comments") val comments : Link,
    @SerializedName("review_comments") val review_comments : Link,
    @SerializedName("review_comment") val review_comment : Link,
    @SerializedName("commits") val commits : Link,
    @SerializedName("statuses") val statuses : Link
)

data class Link (

    @SerializedName("href") val href : String
)

data class Teams (

    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("url") val url : String,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("name") val name : String,
    @SerializedName("slug") val slug : String,
    @SerializedName("description") val description : String,
    @SerializedName("privacy") val privacy : String,
    @SerializedName("permission") val permission : String,
    @SerializedName("members_url") val members_url : String,
    @SerializedName("repositories_url") val repositories_url : String
)


data class Repo (

    @SerializedName("id") val id : Int,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("name") val name : String,
    @SerializedName("full_name") val full_name : String,
    @SerializedName("owner") val owner : User,
    @SerializedName("private") val private : Boolean,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("description") val description : String,
    @SerializedName("fork") val fork : Boolean,
    @SerializedName("url") val url : String,
    @SerializedName("archive_url") val archive_url : String,
    @SerializedName("assignees_url") val assignees_url : String,
    @SerializedName("blobs_url") val blobs_url : String,
    @SerializedName("branches_url") val branches_url : String,
    @SerializedName("collaborators_url") val collaborators_url : String,
    @SerializedName("comments_url") val comments_url : String,
    @SerializedName("commits_url") val commits_url : String,
    @SerializedName("compare_url") val compare_url : String,
    @SerializedName("contents_url") val contents_url : String,
    @SerializedName("contributors_url") val contributors_url : String,
    @SerializedName("deployments_url") val deployments_url : String,
    @SerializedName("downloads_url") val downloads_url : String,
    @SerializedName("events_url") val events_url : String,
    @SerializedName("forks_url") val forks_url : String,
    @SerializedName("git_commits_url") val git_commits_url : String,
    @SerializedName("git_refs_url") val git_refs_url : String,
    @SerializedName("git_tags_url") val git_tags_url : String,
    @SerializedName("git_url") val git_url : String,
    @SerializedName("issue_comment_url") val issue_comment_url : String,
    @SerializedName("issue_events_url") val issue_events_url : String,
    @SerializedName("issues_url") val issues_url : String,
    @SerializedName("keys_url") val keys_urls_url : String,
    @SerializedName("labels_url") val labels_url : String,
    @SerializedName("languages_url") val languages_url : String,
    @SerializedName("merges_url") val merges_url : String,
    @SerializedName("milestones_url") val milestones_url : String,
    @SerializedName("notifications_url") val notifications_url : String,
    @SerializedName("pulls_url") val pulls_url : String,
    @SerializedName("releases_url") val releases_url : String,
    @SerializedName("ssh_url") val ssh_url : String,
    @SerializedName("stargazers_url") val stargazers_url : String,
    @SerializedName("statuses_url") val statuses_url : String,
    @SerializedName("subscribers_url") val subscribers_url : String,
    @SerializedName("subscription_url") val subscription_url : String,
    @SerializedName("tags_url") val tags_url : String,
    @SerializedName("teams_url") val teams_url : String,
    @SerializedName("trees_url") val trees_url : String,
    @SerializedName("clone_url") val clone_url : String,
    @SerializedName("mirror_url") val mirror_url : String,
    @SerializedName("hooks_url") val hooks_url : String,
    @SerializedName("svn_url") val svn_url : String,
    @SerializedName("homepage") val homepage : String,
    @SerializedName("language") val language : String,
    @SerializedName("forks_count") val forks_count : Int,
    @SerializedName("stargazers_count") val stargazers_count : Int,
    @SerializedName("watchers_count") val watchers_count : Int,
    @SerializedName("size") val size : Int,
    @SerializedName("default_branch") val default_branch : String,
    @SerializedName("open_issues_count") val open_issues_count : Int,
    @SerializedName("is_template") val is_template : Boolean,
    @SerializedName("topics") val topics : List<String>,
    @SerializedName("has_issues") val has_issues : Boolean,
    @SerializedName("has_projects") val has_projects : Boolean,
    @SerializedName("has_wiki") val has_wiki : Boolean,
    @SerializedName("has_pages") val has_pages : Boolean,
    @SerializedName("has_downloads") val has_downloads : Boolean,
    @SerializedName("archived") val archived : Boolean,
    @SerializedName("disabled") val disabled : Boolean,
    @SerializedName("visibility") val visibility : String,
    @SerializedName("pushed_at") val pushed_at : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("permissions") val permissions : Permissions,
    @SerializedName("allow_rebase_merge") val allow_rebase_merge : Boolean,
    @SerializedName("template_repository") val template_repository : String,
    @SerializedName("temp_clone_token") val temp_clone_token : String,
    @SerializedName("allow_squash_merge") val allow_squash_merge : Boolean,
    @SerializedName("delete_branch_on_merge") val delete_branch_on_merge : Boolean,
    @SerializedName("allow_merge_commit") val allow_merge_commit : Boolean,
    @SerializedName("subscribers_count") val subscribers_count : Int,
    @SerializedName("network_count") val network_count : Int,
    @SerializedName("license") val license : License,
    @SerializedName("forks") val forks : Int,
    @SerializedName("open_issues") val open_issues : Int,
    @SerializedName("watchers") val watchers : Int
)

data class Permissions (

    @SerializedName("admin") val admin : Boolean,
    @SerializedName("push") val push : Boolean,
    @SerializedName("pull") val pull : Boolean
)

data class License (

    @SerializedName("key") val key : String,
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String,
    @SerializedName("spdx_id") val spdx_id : String,
    @SerializedName("node_id") val node_id : String,
    @SerializedName("html_url") val html_url : String
)