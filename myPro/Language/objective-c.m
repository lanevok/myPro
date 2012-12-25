/*
 * iPhone アプリ開発
 * Objective-C 超基本
 *
 */

// 変数
{
	int num = 10;
	float x = 10.5;
	BOOL flag = YES;
}

// 文字列作成
{
	NSString *str = [NSString stringWithString:@"Hello"];
	// NS とは NextStep (=OS)
	// NSMutableString だと 修正が行える
}

// コンソール出力
{
	NSLog(@"%@",str);
	// 文字列として取りだす (整数→文字列)
}

// 文字列表示
{
	NSString *str2 = [NSString stringWithFormat:@"%d",str];
	// %f も使える
}

// 配列
{
	NSArray *array = [NSArray arrayWithObjects:@"A",@"B",@"C",nil];
	// 末端にnil文字を付加する必要がある
}

// 要素数(カウント)
{
	NSLog(@"%d",array.count);
}

// 配列番号指定読み出し
{
	NSLog(@"%@",[array objectAtIndex:1]);
}

// 多量のテータを扱う
{
	NSDictionary *dict = [NSDictionary dictionaryWithObjectsAndKeys:
		@"Apple",@"A",
		@"Box",@"B",
		@"Camp",@"C",
		,nil];
	// [value,key] と順序で格納することに注意

	// 全キーワードを取得する
	{
		NSLog(@"%@",dict.allKeys);
		// for文を使う必要がない
	}
	// 要素の表示
	{
		NSLog(@"%@",[dict ObjectForKey:@"A"]);
	}
}

// 比較
{
	NSString *a = [NSString stringWithString:@"AAA"];
	NSString *b = [NSString stringWithFormat:@"%@",@"AAA"];

	if(a==b){
		NSLog(@"a==b");
	}
	else{
		NSLog(@"a!=b");
	}
	if([a isEqual:b]){
		NSLog(@"a isEqual b");
	}
	else{
		NSLog(@"!(a isEqual b)");
	}

	/* Output
	
	a!=b
	a isEqual b

	*/
}
