/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2023, Jrod7938, Khang-ALe, jesma14, Holesum
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jrod7938.textchangeapp.model

/**
 * Model class for an InterestObject, created when a user is interested in a book listing
 *
 * @property interestId String id of the InterestObject created with the id of the interested user and the book
 * @property userDisplayName String of the interested user's display name
 * @property buyerConfirm Boolean value for whether the Buyer approves the sale
 * @property sellerConfirm Boolean value for whether the Seller approves the sale
 *
 **/
data class InterestObject(
    val interestId: String,
    val userDisplayName: String,
    var buyerConfirm: Boolean,
    var sellerConfirm: Boolean
) {

    companion object {

        /**
         *
         * Creates an InterestObject from a HashMap
         * This is how it is stored in Firebase
         *
         * @param map the HashMap to create the InterestObject from
         *
         * @return InterestObject the InterestObject created from the HashMap
         */
        fun fromMap(map: HashMap<String, Any>): InterestObject {
            return InterestObject(
                interestId = map["interest_id"].toString(),
                userDisplayName = map["user_display_name"].toString(),
                buyerConfirm = map["buyer_confirm"] as Boolean,
                sellerConfirm = map["seller_confirm"] as Boolean,
            )
        }
    }

    /**
     *
     * Creates a map from the InterestObject
     *
     * @return MutableMap<String, Any> the map created from the InterestObject
     */

    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "interest_id" to this.interestId,
            "user_display_name" to this.userDisplayName,
            "buyer_confirm" to this.buyerConfirm,
            "seller_confirm" to this.sellerConfirm
        )
    }
}