package com.ppk.graphql.domain.repository

import com.apollographql.apollo3.api.ApolloResponse
import com.ppk.graphql.CharacterQuery
import com.ppk.graphql.CharactersListQuery


interface GetCharacterListRepository {
    suspend fun getCharacterList():ApolloResponse<CharactersListQuery.Data>

    suspend fun getCharacter(id:String):ApolloResponse<CharacterQuery.Data>
}