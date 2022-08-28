package com.ppk.graphql.domain.interactor

import com.apollographql.apollo3.api.ApolloResponse
import com.ppk.graphql.CharacterQuery
import com.ppk.graphql.CharactersListQuery
import com.ppk.graphql.domain.repository.GetCharacterListRepository

/**
 * since this is the simple project, I combine all use case in the same place. This is the bad habit
 */
class GetLaunchListUseCase constructor(val getCharacterListRepository: GetCharacterListRepository)  {


    suspend fun getCharacterList(): ApolloResponse<CharactersListQuery.Data> {
        return getCharacterListRepository.getCharacterList()}

    suspend fun getCharacter(id:String):ApolloResponse<CharacterQuery.Data> {

       return getCharacterListRepository.getCharacter(id)}
}