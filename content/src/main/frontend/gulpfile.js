const gulp        = require('gulp');
const sass        = require('gulp-sass');
const header      = require('gulp-header');
const cleanCSS   = require('gulp-clean-css');
const rename =  require('gulp-rename');
var concatCss = require('gulp-concat-css');

const apache2License = [
'/*',
' * Licensed to the Apache Software Foundation (ASF) under one or more',
' * contributor license agreements.  See the NOTICE file distributed with',
' * this work for additional information regarding copyright ownership.',
' * The ASF licenses this file to You under the Apache License, Version 2.0',
' * (the "License"); you may not use this file except in compliance with',
' * the License.  You may obtain a copy of the License at',
' *',
' *      http://www.apache.org/licenses/LICENSE-2.0',
' *',
' * Unless required by applicable law or agreed to in writing, software',
' * distributed under the License is distributed on an "AS IS" BASIS,',
' * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.',
' * See the License for the specific language governing permissions and',
' * limitations under the License.',
' */',
''
].join('\n');

function srcDir(sub) {
    return `./src/${sub}`;
}
function distDir(sub) {
    return `./dist/${sub}`;
}

gulp.task('starter-styles', () => gulp.src(srcDir('scss/*.scss'))
    .pipe(sass().on('error', sass.logError))
    .pipe(cleanCSS())
    .pipe(concatCss('bundle.css'))
    .pipe(header(apache2License))
    .pipe(gulp.dest(distDir('jcr_root/content/starter/css'))));

gulp.task('starter-assets', () => gulp.src(srcDir('img/*'))
    .pipe(gulp.dest(distDir('jcr_root/content/starter/img'))));

gulp.task('starter-fonts', () => gulp.src([srcDir('fonts/*')])
    .pipe(gulp.dest(distDir('jcr_root/content/starter/fonts'))));


gulp.task('starter-logo', () => gulp.src(srcDir('img/sling-logo.svg'))
    .pipe(gulp.dest(distDir('jcr_root/content/starter'))));

gulp.task('default', gulp.series('starter-styles', 'starter-assets', 'starter-fonts', 'starter-logo'));

