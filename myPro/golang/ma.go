package mylib

import (
	"fmt"
	"github.com/deckarep/golang-set"
	"github.com/ikawaha/kagome/tokenizer"
	"strings"
)

/*
 * textを形態素解析をし、数を除く名詞のみ出現回数を数え
 * map[単語 string]回数int で返します。
 * ※ map に格納される語は出現2回以上で、一部ストップワード除去。
 */
func GetNounMapForMA(text string) map[string]int {
	res := make(map[string]int)

	t := tokenizer.New()
	tokens := t.Tokenize(text)
	for _, token := range tokens {
		features := strings.Join(token.Features(), ",")

		if strings.HasPrefix(features, "名詞") &&
			!strings.Contains(features, "数") {

			w := fmt.Sprintf("%s", token.Surface)
			if res[w] == 0 {
				res[w] = 1
			} else {
				res[w] += 1
			}

		}
	}

	stopWord := []string{"http", "htm", "com", "co", "jp",
		","://","/","&#",",",".","-","www",
		"&", ";", ":", ":&", ",'", "(", ")", "+", ";&", ";-"}

	set := mapset.NewSet()
	for _, v := range stopWord {
		set.Add(v)
	}

	for key, value := range res {
		if value == 1 || set.Contains(key) {
			delete(res, key)
		}
	}

	return res
}
