package student

class Validator {
    companion object {
        private var telephoneRegex = Regex("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val gitRegex = Regex("(https?:\\/\\/)?(www\\.)?(github\\.com|gitlab\\.com|bitbucket\\.org)\\/[A-Za-z0-9_.-]+\\/[A-Za-z0-9_.-]+(\\.git)?\$")

        internal fun isTelephoneNumberValid(phone: String?): Boolean {
            return phone?.matches(telephoneRegex) ?: true
        }

        internal fun isEmailValid(email: String?): Boolean{
            return email?.matches(emailRegex) ?: true
        }

        internal fun isTelegramValid(telegram: String?): Boolean{
            return telegram?.matches(telegramRegex) ?: true
        }

        internal fun isGitValid(git: String?): Boolean{
            return git?.matches(gitRegex) ?: true
        }
}
}