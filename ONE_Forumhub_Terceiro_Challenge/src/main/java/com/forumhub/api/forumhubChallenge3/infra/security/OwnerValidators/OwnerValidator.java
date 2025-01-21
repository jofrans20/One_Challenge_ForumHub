package com.forumhub.api.forumhubChallenge3.infra.security.OwnerValidators;

import com.forumhub.api.forumhubChallenge3.infra.security.SecurityContexHolderAccess;


public interface OwnerValidator extends SecurityContexHolderAccess {
    void validate(Long EntityId);
}
