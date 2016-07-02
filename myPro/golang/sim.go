package mylib

import (
	"math"
	"sort"
)

type Bow struct {
	id  int
	bow map[string]int
	sim float64
}

type Bowlist []Bow

func (bl Bowlist) Len() int {
	return len(bl)
}

func (bl Bowlist) Swap(i, j int) {
	bl[i], bl[j] = bl[j], bl[i]
}

func (bl Bowlist) Less(i, j int) bool {
	return bl[i].sim > bl[j].sim
}

/*
 * targetの類似TopNをsliceで返す。
 * ※ sim> 0.4 以上に限定。
 */
func GetTopNid(targetId int, target map[string]int, src map[int]map[string]int, n int) []int {
	var bowlist Bowlist = []Bow{}

	for key, srcMap := range src {
		nowBow := Bow{key, srcMap, GetCosSim(target, srcMap)}
		bowlist = append(bowlist, nowBow)
	}

	sort.Sort(bowlist)

	if len(bowlist) < n {
		n = len(bowlist)
	}
	var res = []int{}
	for i := 0; i < n; i++ {
		if bowlist[i].sim > 0.4 && bowlist[i].id != targetId {
			res = append(res, bowlist[i].id)
		}
	}

	return res
}

/*
 * map[string]int 間の Cosine Simlarity を float64 で返す。
 */
func GetCosSim(a map[string]int, b map[string]int) float64 {
	var nume, deno_a, deno_b float64

	for idx := range a {
		if b[idx] != 0 {
			nume += float64(a[idx] * b[idx])
		}
	}

	for _, v := range a {
		deno_a += float64(v * v)
	}
	for _, v := range b {
		deno_b += float64(v * v)
	}

	deno_a = math.Sqrt(deno_a)
	deno_b = math.Sqrt(deno_b)

	return nume / (deno_a * deno_b)
}
